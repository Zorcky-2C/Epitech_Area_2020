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

import com.example.spotifyapp.Services.Spotify.SpotifyUserActivity;
import com.example.spotifyapp.SpotifyPlaylist.MainSpotifyPlaylistActivity;
import com.example.spotifyapp.SpotifyPlaylist.SearchSpotifyPlaylistActivity;


public class HomeSpotifyWidgetActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    ImageView Profile;

    static CardView SpotifySUser, SpotifySMusic, SpotifyPlaylist;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView img;

    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
            SpotifyStatus, SpotifySUserStatus, SpotifySMusicStatus, SpotifyPlaylistStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_spotify);

        drawerLayout = findViewById(R.id.drawer_layout);
        SpotifyPlaylist = findViewById(R.id.SpotifyPlaylistCase);
        SpotifySMusic = findViewById(R.id.SpotifySMusicCase);
        SpotifySUser = findViewById(R.id.SpotifySUserCase);
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
                startActivity(new Intent(HomeSpotifyWidgetActivity.this, ProfilUserActivity.class));
            }
        });

        SpotifySMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSpotifySMusic(view);
            }
        });

        SpotifySUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSpotifySUser(view);
            }
        });

        SpotifyPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSpotifyPlaylist(view);
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
        if (SettingsSpotifyActivity.getSwitchCineStatus() == 1) {
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchWeatherStatus() == 1) {
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchCovidStatus() == 1) {
            CovidL.setVisibility(View.VISIBLE);
            covidStatus = 1;
        } else {
            CovidL.setVisibility(View.GONE);
            covidStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchfacebookStatus() == 1) {
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchPaypalStatus() == 1) {
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchImgurStatus() == 1) {
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchSpotifyStatus() == 1) {
            SpotifyL.setVisibility(View.VISIBLE);
            SpotifyStatus = 1;
        } else {
            SpotifyL.setVisibility(View.GONE);
            SpotifyStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchSpotifySMusicStatus() == 1) {
            SpotifySMusic.setVisibility(View.VISIBLE);
            SpotifySMusicStatus = 1;
        } else {
            SpotifySMusic.setVisibility(View.GONE);
            SpotifySMusicStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchSpotifySUserStatus() == 1) {
            SpotifySUser.setVisibility(View.VISIBLE);
            SpotifySUserStatus = 1;
        } else {
            SpotifySUser.setVisibility(View.GONE);
            SpotifySUserStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchSpotifyPlaylistStatus() == 1) {
            SpotifyPlaylist.setVisibility(View.VISIBLE);
            SpotifyPlaylistStatus = 1;
        } else {
            SpotifyPlaylist.setVisibility(View.GONE);
            SpotifyPlaylistStatus = 0;
        }
    }


    public static int getSwitchSpotifyStatus()
    {
        return SpotifyStatus;
    }
    public static int getSwitchSpotifySUserStatus()
    {
        return SpotifySUserStatus;
    }
    public static int getSwitchSpotifySMusicStatus()
    {
        return SpotifySMusicStatus;
    }
    public static int getSwitchSpotifPlaylistStatus()
    {
        return SpotifyPlaylistStatus;
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
        redirectActivity(this, HomeActivity.class);
    }

    public void ClickSettings(View view) {
        redirectActivity(this, SettingsSpotifyActivity.class);
    }

    public void ClickSpotifySUser(View view) {
        redirectActivity(this, SpotifyUserActivity.class);
    }
    public void ClickSpotifySMusic(View view) {
        redirectActivity(this, SearchSpotifyPlaylistActivity.class);
    }
    public void ClickSpotifyPlaylist(View view) {
        redirectActivity(this, MainSpotifyPlaylistActivity.class);
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