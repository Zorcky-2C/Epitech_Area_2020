package com.example.spotifyapp.Services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.spotifyapp.Fun.CineActivity;
import com.example.spotifyapp.HomeActivity;
import com.example.spotifyapp.HomeFacebookWidgetActivity;
import com.example.spotifyapp.HomeImgurWidgetActivity;
import com.example.spotifyapp.HomeSpotifyWidgetActivity;
import com.example.spotifyapp.News.CovidActivity;
import com.example.spotifyapp.News.WeatherActivity;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.ImgurModel.MainActivityEpicture;
import com.example.spotifyapp.SettingsActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FavouriteImgur extends AppCompatActivity {
    String my_accessToken = null;
    String my_refreshToken = null;
    String my_username = null;
    String imgurImage_url = null;


    MainActivityEpicture loginacces = new MainActivityEpicture();


    DrawerLayout drawerLayout;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    static int  cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus, SpotifyStatus;

    ImageView homImgur;
    ImageView profile;



    static public String username;
    static public String refresh;
    static public String access;

    private static final String TAG = MainActivityEpicture.class.getSimpleName();
    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_imgur);


        drawerLayout = findViewById(R.id.drawer_layout);

        my_username = loginacces.nametoken();
        my_refreshToken = loginacces.refreshtoken();
        my_accessToken= loginacces.accesstoken();

        homImgur = findViewById(R.id.homeImgur);
        profile = findViewById(R.id.profileImgur);

        Intent my_intent = getIntent();
        if (my_intent != null) {
            my_accessToken = my_intent.getStringExtra("EXTRA_ACCESS_TOKEN");
            my_refreshToken = my_intent.getStringExtra("EXTRA_REFRESH_TOKEN");
            my_username = my_intent.getStringExtra("EXTRA_USERNAME");
        }
        uploadImage();

        homImgur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteImgur.this, EpictureMain.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteImgur.this, ProfileImgur.class));
            }
        });
        //setting setup

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        SpotifyL = findViewById(R.id.spotifyLayout);
        setVisibilityWidget();

        username = my_username;
        refresh = my_refreshToken;
        access = my_accessToken;
        getImage("http://10.0.2.2:8080/ImgurAuth/Image");

    }


    public static String keyUsername() {
        return username;
    }

    public static String keyRefresh() {
        return refresh;
    }

    public static String keyAccess() {
        return access;
    }

    public static void MyUsername(String my_new_username) {
        username = my_new_username;
    }

    public static void MyKeyAccess(String my_new_access) {
        access = my_new_access;
    }

    public static void MyKeyRefresh(String my_new_keyfresh) {
        username = my_new_keyfresh;
    }

    public void uploadImage() {
        httpClient = new OkHttpClient.Builder().build();
        //String url = "https://api.imgur.com/3/account/" + my_username + "/favorites/";
        String url = "http://10.0.2.2:8080/ImgurAuth/FavPage";

        String json = "{\"authorization\" : \""+ "Client-ID 2479bf1d5fdd677" +"\", " +
                "\"userAgent\" : \""+ "Epicture" + "\", " +
                "\"username\" : \""+ my_username +"\", " +
                "\"param\" : \""+ "/favorites/" + "\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                //.header("Authorization", "Client-ID 2479bf1d5fdd677")
                //.header("User-Agent", "Epicture")
                .post(body)
                .build();

        httpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e(TAG, "An error has occurred " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    JSONObject data = new JSONObject(response.body().string());
                    JSONArray items = data.getJSONArray("data");
                    final List<Photo> photos = new ArrayList<>();

                    for (int i = 0; i < items.length(); i++)
                    {
                        JSONObject item = items.getJSONObject(i);
                        Photo photo = new Photo();
                        if(item.getBoolean("is_album")) {
                            photo.id = item.getString("cover");
                        } else {
                            photo.id = item.getString("id");
                        }
                        photo.title = item.getString("title");

                        photos.add(photo);

                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                render(photos);
                            }
                        });
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class PhotoVH extends RecyclerView.ViewHolder {
        AppCompatImageView photo;
        AppCompatTextView title;

        public PhotoVH(View itemView) {
            super(itemView);
        }
    }

    private void render(final List<Photo> photos)
    {
        RecyclerView recyclerView = findViewById(R.id.rv_of_photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.Adapter<PhotoVH> adapter = new RecyclerView.Adapter<PhotoVH>()
        {
            @NonNull
            @Override
            public PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                PhotoVH vh = new PhotoVH(getLayoutInflater().inflate(R.layout.list_image_imgur, null));
                vh.photo = vh.itemView.findViewById(R.id.photo);
                vh.title = vh.itemView.findViewById(R.id.title);
                return vh;
            }

            @Override
            public void onBindViewHolder(@NonNull PhotoVH holder, int position)
            {
                /*OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://10.0.2.2:8080/ImgurAuth/Image").build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("Error: ", " no responses");
                        Log.d("Error message: ", e.getMessage());
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            imgurImage_url = response.body().string();
                        }
                    }
                });*/



                Picasso.with(FavouriteImgur.this)
                        //.load("https://i.imgur.com/" + photos.get(position).id + ".jpg")
                        .load(imgurImage_url + photos.get(position).id + ".jpg")
                        .into(holder.photo);
                holder.title.setText(photos.get(position).title);
            }

            @Override
            public int getItemCount()
            {
                return photos.size();
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public void getImage(String url, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void getImage(String url){
        getImage(url, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            try {
                                imgurImage_url = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.d("Log: ", "GetImage is successful.");
                        }else{
                            Log.d("App: ", "GetImage failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            Log.d("Response Body", imgurImage_url);
                        }
                    }
                });
            }
        });
    }

    private static class Photo {
        String id;
        String title;
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
    public void ClickSpotify(View view){ HomeActivity.redirectActivity(this, HomeSpotifyWidgetActivity.class);
    }
    /*public void ClickImgur(View view){
        HomeActivity.redirectActivity(this, HomeImgurWidgetActivity.class);
    }*/

    public void ClickImgur(View view){
        if (MainActivityEpicture.getStatusofToken() == 0) {
            HomeActivity.redirectActivity(this, MainActivityEpicture.class);
        } else {
            HomeActivity.redirectActivity(this, EpictureMain.class);
        }
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

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }
}
