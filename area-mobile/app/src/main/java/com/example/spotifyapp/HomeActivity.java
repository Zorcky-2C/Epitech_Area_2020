package com.example.spotifyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.spotifyapp.Fun.CineActivity;
import com.example.spotifyapp.News.CovidActivity;
import com.example.spotifyapp.News.WeatherActivity;
import com.example.spotifyapp.Services.ImgurModel.MainActivityEpicture;
import com.example.spotifyapp.Services.PaypalActivity;


public class HomeActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    ImageView Profile;

    static CardView Cinema, Weather, Covid, Facebook,
            Paypal, Imgur, Spotify;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView img;

    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
            SpotifyStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        Cinema = findViewById(R.id.theaterCase);
        Weather = findViewById(R.id.weatherCase);
        Covid = findViewById(R.id.covidCase);
        Facebook = findViewById(R.id.facebookCase);
        Paypal = findViewById(R.id.paypalCase);
        Imgur = findViewById(R.id.imgurCase);
        Spotify =findViewById(R.id.spotifyCase);
        img = findViewById(R.id.settingWidget);

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        SpotifyL = findViewById(R.id.spotifyLayout);

        Profile = findViewById(R.id.profile);

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ProfilUserActivity.class));
            }
        });

        Cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickTheater(view);
            }
        });
        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickWeather(view);
            }
        });
        Covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickCovid(view);
            }
        });
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickFacebook(view);
            }
        });
        Paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickPaypal(view);
            }
        });
        Imgur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImgur(view);
            }
        });
        Spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSpotify(view);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSettings(view);
            }
        });

        setVisibilityWidget();
    }


    //setting setup

    public void setVisibilityWidget()
    {
        if (SettingsActivity.getSwitchCineStatus() == 1) {
            Cinema.setVisibility(View.VISIBLE);
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            Cinema.setVisibility(View.GONE);
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsActivity.getSwitchWeatherStatus() == 1) {
            Weather.setVisibility(View.VISIBLE);
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            Weather.setVisibility(View.GONE);
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsActivity.getSwitchCovidStatus() == 1) {
            Covid.setVisibility(View.VISIBLE);
            CovidL.setVisibility(View.VISIBLE);
            covidStatus = 1;
        } else {
            Covid.setVisibility(View.GONE);
            CovidL.setVisibility(View.GONE);
            covidStatus = 0;
        }
        if (SettingsActivity.getSwitchfacebookStatus() == 1) {
            Facebook.setVisibility(View.VISIBLE);
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            Facebook.setVisibility(View.GONE);
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsActivity.getSwitchPaypalStatus() == 1) {
            Paypal.setVisibility(View.VISIBLE);
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            Paypal.setVisibility(View.GONE);
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsActivity.getSwitchImgurStatus() == 1) {
            Imgur.setVisibility(View.VISIBLE);
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            Imgur.setVisibility(View.GONE);
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
        if (SettingsActivity.getSwitchSpotifyStatus() == 1) {
            Spotify.setVisibility(View.VISIBLE);
            SpotifyL.setVisibility(View.VISIBLE);
            SpotifyStatus = 1;
        } else {
            Spotify.setVisibility(View.GONE);
            SpotifyL.setVisibility(View.GONE);
            SpotifyStatus = 0;
        }
    }


    public static int getSwitchCinemaStatusHome()
    {
        return cinemaStatus;
    }
    public static int getSwitchWeatherStatusHome()
    {
        return weatherStatus;
    }
    public static int getSwitchCovidStatusHome()
    {
        return covidStatus;
    }
    public static int getSwitchFacebookStatusHome()
    {
        return facebookStatus;
    }
    public static int getSwitchPaypalStatusHome()
    {
        return paypalStatus;
    }
    public static int getSwitchImgurStatusHome()
    {
        return ImgurStatus;
    }
    public static int getSwitchSpotifyStatus()
    {
        return SpotifyStatus;
    }


    //The Menu code
    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer((GravityCompat.START));
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        recreate();
    }

    public void ClickTheater(View view) {
        redirectActivity(this, CineActivity.class);
    }

    public void ClickWeather(View view) {
        redirectActivity(this, WeatherActivity.class);
    }

    public void ClickImgur(View view) {
            redirectActivity(this, MainActivityEpicture.class);
    }

    public void ClickFacebook(View view) {
        redirectActivity(this, HomeFacebookWidgetActivity.class);
    }

    public void ClickSpotify(View view) {
        redirectActivity(this, HomeSpotifyWidgetActivity.class);
    }

    public void ClickPaypal(View view) {
        redirectActivity(this, PaypalActivity.class);
    }

    public void ClickSettings(View view) {
        redirectActivity(this, SettingsActivity.class);
    }

    public void ClickCovid(View view) {
        redirectActivity(this, CovidActivity.class);
    }

    public void ClickProfile(View view) {
        redirectActivity(this, ProfilUserActivity.class);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}