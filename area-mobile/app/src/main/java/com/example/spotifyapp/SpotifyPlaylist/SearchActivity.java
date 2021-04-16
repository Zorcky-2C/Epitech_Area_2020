package com.example.spotifyapp.SpotifyPlaylist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.spotifyapp.R;
import com.example.spotifyapp.SpotifyPlaylist.models.Playlist;
import com.example.spotifyapp.SpotifyPlaylist.models.Track;
import com.google.gson.Gson;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.spotifyapp.SpotifyPlaylist.Config.CLIENT_ID;
import static com.example.spotifyapp.SpotifyPlaylist.Config.REDIRECT_URI;
import static com.example.spotifyapp.SpotifyPlaylist.Config.TRACK_URI;
import static com.example.spotifyapp.SpotifyPlaylist.Config.mAccessToken;
import static com.example.spotifyapp.SpotifyPlaylist.Config.mCall;
import static com.example.spotifyapp.SpotifyPlaylist.Config.mOkHttpClient;
import static com.example.spotifyapp.SpotifyPlaylist.Config.mSpotifyAppRemote;


public class SearchActivity extends AppCompatActivity {
    Context context;

    String search_url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        context = this;
        Config.connect(true, context);
        ListView list = (ListView) findViewById(R.id.songs);
        ArrayList<Track> arrayList = new ArrayList<>();

        if (getIntent().hasExtra("json")) {
            try {
                JSONArray tracks = new JSONArray(getIntent().getStringExtra("json"));
                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject x = tracks.getJSONObject(i);
                    Track t = new Track();
                    t.setName(x.getString("name"));
                    t.setUri(x.getString("uri"));
                    t.setArtist(x.getJSONArray("artists").getJSONObject(0).getString("name"));
                    arrayList.add(t);
                }
                ArrayAdapter<Track> listAdapter = new SongAdapter(this, R.layout.search_row, arrayList);
                list.setAdapter(listAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (getIntent().hasExtra("name")) {
            TextView nameView = findViewById(R.id.searched_song);
            nameView.setText(getIntent().getStringExtra("name"));
        }
    }


    private void setResponse(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView responseView = findViewById(R.id.response_text_view);
                responseView.setText(text);
            }
        });
    }


    private AuthorizationRequest getAuthenticationRequest(AuthorizationResponse.Type type) {
        return new AuthorizationRequest.Builder(CLIENT_ID, type, getRedirectUri().toString())
                .setShowDialog(false)
                .setScopes(new String[]{"user-read-email"})
                .setCampaign("your-campaign-token")
                .build();
    }

    private Uri getRedirectUri() {
        return Uri.parse(REDIRECT_URI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help) {
            openTutorial();
        }
        return (super.onOptionsItemSelected(item));
    }

    public void openTutorial() {
        Intent intent = new Intent(this, TutorialActivity.class);
        intent.putExtra("help", "search");
        startActivity(intent);
    }
    private class SongAdapter extends ArrayAdapter<Track> {

        private ArrayList<Track> items;

        public SongAdapter(Context context, int textViewResourceId, ArrayList<Track> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.search_row, null);
            }
            Track o = items.get(position);
            if (o != null) {
                final TextView name = v.findViewById(R.id.name);
                name.setText(o.getArtist()+" - "+o.getName());
                AppCompatImageButton add = v.findViewById(R.id.add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            addToPlaylist(o);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            return v;
        }

        public void addToPlaylist(Track track) throws MalformedURLException {
            ArrayList<Playlist> list = new ArrayList<>();
            TRACK_URI = track.getUri();
            //URL url = new URL("https://api.spotify.com/v1/me/playlists");
            URL url = new URL("http://10.0.2.2:8080/Spotify/addToPlaylist");
            String json = "{\"authorization\" : \""+ "Bearer " + mAccessToken + "\"}";
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            final Request request = new Request.Builder()
                    .url(url)
                    //.addHeader("Authorization", "Bearer " + mAccessToken)
                    .post(body)
                    .build();
            //cancelCall();
            mCall = mOkHttpClient.newCall(request);

            mCall.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        final JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray playlists = jsonObject.getJSONArray("items");
                        Intent intent = new Intent(context, ListPlaylistActivity.class);
                        intent.putExtra("playlists", playlists.toString());
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
