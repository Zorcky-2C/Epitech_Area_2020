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
import com.example.spotifyapp.Services.FacebookListActivity;
import com.example.spotifyapp.Services.FacebookProfilActivity;
import com.example.spotifyapp.Services.FacebookShare;
import com.example.spotifyapp.Services.FavouriteImgur;
import com.example.spotifyapp.Services.ProfileImgur;


public class HomeFacebookWidgetActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    ImageView Profile;

    static CardView FacebookProfile, FacebookPost, FacebookListF;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView img;

    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
            SpotifyStatus, FacebookListFStatus, FacebookProfilStatus, FacebookPostStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_facebook_widget);

        drawerLayout = findViewById(R.id.drawer_layout);

        FacebookProfile = findViewById(R.id.FacebookProfileCase);
        FacebookPost = findViewById(R.id.FacebookPostCase);
        FacebookListF = findViewById(R.id.FacebookListFCase);
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
                startActivity(new Intent(HomeFacebookWidgetActivity.this, ProfilUserActivity.class));
            }
        });

        FacebookListF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickFacebookListF(view);
            }
        });

        FacebookProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickFacebookProfile(view);
            }
        });

        FacebookPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickFacebookPost(view);
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
        if (SettingsFacebookActivity.getSwitchFacebookPostStatus() == 1) {
            FacebookPost.setVisibility(View.VISIBLE);
            FacebookPostStatus = 1;
        } else {
            FacebookPost.setVisibility(View.GONE);
            FacebookPostStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitcFacebookProfileStatus() == 1) {
            FacebookProfile.setVisibility(View.VISIBLE);
            FacebookProfilStatus = 1;
        } else {
            FacebookProfile.setVisibility(View.GONE);
            FacebookProfilStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchFacebookListFStatus() == 1) {
            FacebookListF.setVisibility(View.VISIBLE);
            FacebookListFStatus = 1;
        } else {
            FacebookListF.setVisibility(View.GONE);
            FacebookListFStatus = 0;
        }
    }


    public static int getSwitchSpotifyStatus()
    {
        return SpotifyStatus;
    }
    public static int getSwitchFacebookListFStatus()
    {
        return FacebookListFStatus;
    }
    public static int getSwitchFacebookProfileStatus()
    {
        return FacebookProfilStatus;
    }
    public static int getSwitchFacebookPostStatus()
    {
        return FacebookPostStatus;
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
        redirectActivity(this, SettingsFacebookActivity.class);
    }

    public void ClickFacebookProfile(View view) {
        redirectActivity(this, FacebookProfilActivity.class);
    }
    public void ClickFacebookPost(View view) {
        redirectActivity(this, FacebookShare.class);
    }
    public void ClickFacebookListF(View view) {
        redirectActivity(this, FacebookListActivity.class);
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