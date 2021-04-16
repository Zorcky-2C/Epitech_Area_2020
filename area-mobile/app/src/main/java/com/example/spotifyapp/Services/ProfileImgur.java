package com.example.spotifyapp.Services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.spotifyapp.Fun.CineActivity;
import com.example.spotifyapp.HomeActivity;
import com.example.spotifyapp.HomeFacebookWidgetActivity;
import com.example.spotifyapp.HomeSpotifyWidgetActivity;
import com.example.spotifyapp.MainActivity;
import com.example.spotifyapp.News.CovidActivity;
import com.example.spotifyapp.News.WeatherActivity;
import com.example.spotifyapp.R;
import com.example.spotifyapp.RegisterActivity;
import com.example.spotifyapp.Services.ImgurModel.MainActivityEpicture;
import com.example.spotifyapp.SettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileImgur extends AppCompatActivity {
    String my_accessToken = null;
    String my_refreshToken = null;
    String my_username = null;

    ImageView btn_fav;
    ImageView btn_profile;

    Button my_logOut;
    TextView my_tdelete;
    TextView my_tusername;
    TextView my_tbio;
    DrawerLayout drawerLayout;
    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    static int  cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus, SpotifyStatus;
    MainActivityEpicture loginacces = new MainActivityEpicture();

    private static final String TAG = MainActivityEpicture.class.getSimpleName();
    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_imgur);

        drawerLayout = findViewById(R.id.drawer_layout);

        my_username = loginacces.nametoken();
        my_refreshToken = loginacces.refreshtoken();
        my_accessToken= loginacces.accesstoken();

        my_logOut = findViewById((R.id.signOutProfil));
        my_tusername = findViewById(R.id.username);
        my_tbio = findViewById(R.id.bio);
        my_tdelete = findViewById(R.id.delete);
        btn_fav = findViewById(R.id.fav);
        btn_profile = findViewById(R.id.profileImgur);

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        SpotifyL = findViewById(R.id.spotifyLayout);
        setVisibilityWidget();

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileImgur.this, FavouriteImgur.class));
            }
        });


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileImgur.this, FavouriteImgur.class));
            }
        });

        my_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });
        my_tdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelAccount();
            }
        });
        my_tusername.setText(my_username);
        httpClient = new OkHttpClient.Builder().build();
        String url = "http://10.0.2.2:8080/ImgurAuth/Profile";
        String json = "{\"authorization\" : \""+ "Client-ID 2479bf1d5fdd677" +"\", " +
                "\"username\" : \""+ my_username + "\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                //.header("Authorization", "Client-ID 2479bf1d5fdd677")
                .url(url)
                .post(body)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                ProfileImgur.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);
                            String bio = json.getJSONObject("data").getString("bio");
                            my_tbio.setText(bio);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    public void DelAccount() {
        //todo delete account task
        Log.e("DELETE", "Delete current connected account");
    }

    public void LogOut() {
        Intent intent = new Intent(this, MainActivityEpicture.class);
        startActivity(intent);
        finish();
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
