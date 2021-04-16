package com.example.spotifyapp.Services.Spotify;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotifyapp.Fun.CineActivity;
import com.example.spotifyapp.HomeActivity;
import com.example.spotifyapp.HomeFacebookWidgetActivity;
import com.example.spotifyapp.HomeImgurWidgetActivity;
import com.example.spotifyapp.HomeSpotifyWidgetActivity;
import com.example.spotifyapp.News.CovidActivity;
import com.example.spotifyapp.News.WeatherActivity;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.PaypalActivity;
import com.example.spotifyapp.Services.Spotify.database.PlaylistWithTracks;
import com.example.spotifyapp.Services.Spotify.spotify.RetrofitClient;
import com.example.spotifyapp.Services.Spotify.spotify.SpotifyService;
import com.example.spotifyapp.Services.Spotify.spotify.model.Image;
import com.example.spotifyapp.Services.Spotify.spotify.model.Item;
import com.example.spotifyapp.Services.Spotify.spotify.model.UserPlaylistResult;
import com.example.spotifyapp.Services.Spotify.spotify.model.UserSearchResult;
import com.example.spotifyapp.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SpotifyUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlaylistSearchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView username;
    private TextView userId;
    private CircleImageView avatarImage;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    static int  cinemaStatus, weatherStatus,
            covidStatus, facebookStatus, paypalStatus, ImgurStatus, SpotifyStatus;

    DrawerLayout drawerLayout;

    Thread readThread;
    DbRepository dbRepository;
    List<PlaylistWithTracks> listOfPlaylistAndTracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        drawerLayout = findViewById(R.id.drawer_layout);

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        SpotifyL = findViewById(R.id.spotifyLayout);

        if(RetrofitClient.getAuthToken().isEmpty()) {
            RetrofitClient.noLoginAlert(SpotifyUserActivity.this);
        }
        username = findViewById(R.id.usernameView);
        userId = findViewById(R.id.userId);
        avatarImage = findViewById(R.id.avatarImage);
        SearchView searchView = (SearchView) findViewById(R.id.userSearchField);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userRetroSearch(query);
                playlistRetroSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        setVisibilityWidget();
    }

    private void userRetroSearch(String query) {
        SpotifyService service = RetrofitClient.getInstance().create(SpotifyService.class);

        Call<UserSearchResult> call = service.searchUser(query, RetrofitClient.getAuthToken());

        call.enqueue(new Callback<UserSearchResult>() {
            @Override
            public void onResponse(Call<UserSearchResult> call, Response<UserSearchResult> response) {
                System.out.println(response.raw().request().url());
                if (response.body() != null) {
                    UserSearchResult userResult = response.body();
                    userId.setText(userResult.getId());
                    username.setText(userResult.getDisplayName());
                    Glide.with(SpotifyUserActivity.this).clear(avatarImage);
                    List<Image> images = userResult.getImages();
                    if (userResult.getImages() != null && images.size() > 0) {
                        String imageUrl = images.get(0).getUrl();
                        Glide.with(SpotifyUserActivity.this).load(imageUrl).placeholder(R.drawable.music_note).into(avatarImage);
                    } else {
                        avatarImage.setImageResource(R.drawable.music_note);
                    }
                } else {
                    Toast.makeText(SpotifyUserActivity.this, "No user found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserSearchResult> call, Throwable t) {
                Toast.makeText(SpotifyUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void playlistRetroSearch(String query) {
        SpotifyService service = RetrofitClient.getInstance().create(SpotifyService.class);
        Call<UserPlaylistResult> call = service.userPlaylist(query, RetrofitClient.getAuthToken());
        call.enqueue(new Callback<UserPlaylistResult>() {
            @Override
            public void onResponse(Call<UserPlaylistResult> call, Response<UserPlaylistResult> response) {
                System.out.println(response.raw().request().url());
                if (response.body() != null) {
                    generatePlaylistResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserPlaylistResult> call, Throwable t) {
                Toast.makeText(SpotifyUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void generatePlaylistResult(UserPlaylistResult playlist) {
        recyclerView = findViewById(R.id.userPlaylistView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        List<Item> itemsList = new ArrayList<>();
        itemsList.addAll(playlist.getItems());
        adapter = new PlaylistSearchAdapter(itemsList,SpotifyUserActivity.this);

    }

    //setting setup

    public void setVisibilityWidget()
    {
        if (SettingsActivity.getSwitchCineStatus() == 1) {
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsActivity.getSwitchWeatherStatus() == 1) {
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsActivity.getSwitchCovidStatus() == 1) {
            CovidL.setVisibility(View.VISIBLE);
            covidStatus = 1;
        } else {
            CovidL.setVisibility(View.GONE);
            covidStatus = 0;
        }
        if (SettingsActivity.getSwitchfacebookStatus() == 1) {
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsActivity.getSwitchPaypalStatus() == 1) {
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsActivity.getSwitchImgurStatus() == 1) {
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
        if (SettingsActivity.getSwitchSpotifyStatus() == 1) {
            SpotifyL.setVisibility(View.VISIBLE);
            SpotifyStatus = 1;
        } else {
            SpotifyL.setVisibility(View.GONE);
            SpotifyStatus = 0;
        }
    }


    //Menu Code
    public void ClickMenu(View view) {
        HomeActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        HomeActivity.redirectActivity(this, HomeActivity.class);
    }

    public void ClickTheater(View view){
        HomeActivity.redirectActivity(this, CineActivity.class);

    }

    public void ClickCovid(View view){
        HomeActivity.redirectActivity(this, CovidActivity.class);
    }

    public void ClickWeather(View view){
        HomeActivity.redirectActivity(this, WeatherActivity.class);
    }

    public void ClickImgur(View view){
        HomeActivity.redirectActivity(this, HomeImgurWidgetActivity.class);
    }

    public void ClickFacebook(View view){
        HomeActivity.redirectActivity(this, HomeFacebookWidgetActivity.class);
    }

    public void ClickPaypal(View view){
        HomeActivity.redirectActivity(this, PaypalActivity.class);
    }

    public void ClickSettings(View view) {
        HomeActivity.redirectActivity(this, SettingsActivity.class);
    }

    public void ClickSpotify(View view){ HomeActivity.redirectActivity(this, HomeSpotifyWidgetActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }

}
