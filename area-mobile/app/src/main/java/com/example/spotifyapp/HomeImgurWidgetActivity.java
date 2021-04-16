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

import com.example.spotifyapp.Services.EpictureMain;
import com.example.spotifyapp.Services.FavouriteImgur;
import com.example.spotifyapp.Services.ProfileImgur;
import com.example.spotifyapp.Services.Spotify.SpotifyUserActivity;
import com.example.spotifyapp.SpotifyPlaylist.MainSpotifyPlaylistActivity;
import com.example.spotifyapp.SpotifyPlaylist.SearchSpotifyPlaylistActivity;


public class HomeImgurWidgetActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    ImageView Profile;

    static CardView ImgurFav, ImgurFeed, ImgurProfile;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView img;

    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
            SpotifyStatus, ImgurFavStatus, ImgurFeedStatus, ImgurProfileStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_imgur_widget);

        drawerLayout = findViewById(R.id.drawer_layout);

        ImgurFav = findViewById(R.id.ImgurFavCase);
        ImgurFeed = findViewById(R.id.ImgurFeedCase);
        ImgurProfile = findViewById(R.id.ImgurProfileCase);
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
                startActivity(new Intent(HomeImgurWidgetActivity.this, ProfilUserActivity.class));
            }
        });

        ImgurProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImgurProfile(view);
            }
        });

        ImgurFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImgurFeed(view);
            }
        });

        ImgurFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickImgurFav(view);
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
        if (SettingsImgurActivity.getSwitchImgurFavStatus() == 1) {
            ImgurFav.setVisibility(View.VISIBLE);
            ImgurFavStatus = 1;
        } else {
            ImgurFav.setVisibility(View.GONE);
            ImgurFavStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchImgurFeedStatus() == 1) {
            ImgurFeed.setVisibility(View.VISIBLE);
            ImgurFeedStatus = 1;
        } else {
            ImgurFeed.setVisibility(View.GONE);
            ImgurFeedStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchImgurProfileStatus() == 1) {
            ImgurProfile.setVisibility(View.VISIBLE);
            ImgurProfileStatus = 1;
        } else {
            ImgurProfile.setVisibility(View.GONE);
            ImgurProfileStatus = 0;
        }
    }


    public static int getSwitchSpotifyStatus()
    {
        return SpotifyStatus;
    }
    public static int getSwitchImgurFavStatus()
    {
        return ImgurFavStatus;
    }
    public static int getSwitchImgurProfileStatus()
    {
        return ImgurProfileStatus;
    }
    public static int getSwitchImgurFeedStatus()
    {
        return ImgurFeedStatus;
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
        redirectActivity(this, SettingsImgurActivity.class);
    }

    public void ClickImgurProfile(View view) {
        redirectActivity(this, ProfileImgur.class);
    }
    public void ClickImgurFav(View view) {
        redirectActivity(this, FavouriteImgur.class);
    }
    public void ClickImgurFeed(View view) {
        redirectActivity(this, EpictureMain.class);
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