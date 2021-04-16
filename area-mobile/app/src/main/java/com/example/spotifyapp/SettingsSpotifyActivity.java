package com.example.spotifyapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SettingsSpotifyActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    static Switch SpotifySUserSwitch, SpotifySMusicSwitch, SpotifyPlaylistSwitch;
    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
    SpotifyStatus, SpotifySUserStatus, SpotifySMusicStatus, SpotifyPlaylistStatus;

    static LinearLayout CinemaL, WeatherL,
            InfosL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_widget_spotify);

        SpotifySUserSwitch = findViewById(R.id.SpotifySUserSwitch);
        SpotifyPlaylistSwitch = findViewById(R.id.SpotifyPlaylistSwitch);
        SpotifySMusicSwitch = findViewById(R.id.SpotifySMusicSwitch);
        drawerLayout = findViewById(R.id.drawer_layout);

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        InfosL = findViewById(R.id.infosLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        SpotifyL = findViewById(R.id.spotifyLayout);

        Home = findViewById(R.id.Home);

        setVisibilityWidgetModel();
        getSwitchStatus();
        setVisibilityWidget();

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickHome(view);
            }
        });
    }
    //Type code here











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
        if (SettingsSpotifyActivity.getSwitchSpotifySUserStatus() == 1) {
            SpotifySUserStatus = 1;
        } else {
            SpotifySUserStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchSpotifySMusicStatus() == 1) {
            SpotifySMusicStatus = 1;
        } else {
            SpotifySMusicStatus = 0;
        }
        if (SettingsSpotifyActivity.getSwitchSpotifyPlaylistStatus() == 1) {
            SpotifyPlaylistStatus = 1;
        } else {
            SpotifyPlaylistStatus = 0;
        }
    }

    public void setVisibilityWidgetModel()
    {
        SpotifySMusicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpotifySMusicStatus = 1;
                } else {
                    // The toggle is disabled
                    SpotifySMusicStatus = 0;
                }
                Log.d("SpotifyMusic : ", String.valueOf(SpotifySMusicStatus));
                SpotifyMusic(String.valueOf(SpotifySMusicStatus));
            }
        });
        SpotifySUserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpotifySUserStatus = 1;
                } else {
                    // The toggle is disabled
                    SpotifySUserStatus = 0;
                }
                Log.d("SpotifyUser : ", String.valueOf(SpotifySUserStatus));
                SpotifyUser(String.valueOf(SpotifySUserStatus));
            }
        });
        SpotifyPlaylistSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpotifyPlaylistStatus = 1;
                } else {
                    // The toggle is disabled
                    SpotifyPlaylistStatus = 0;
                }
                Log.d("SpotifyPlaylist : ", String.valueOf(SpotifyPlaylistStatus));
                SpotifyPlaylist(String.valueOf(SpotifyPlaylistStatus));
            }
        });
    }

    public void getSwitchStatus()
    {
        if (HomeSpotifyWidgetActivity.getSwitchSpotifySMusicStatus() == 1) {
            SpotifySMusicSwitch.setChecked(true);
        } else {
            SpotifySMusicSwitch.setChecked(false);
        }
        if (HomeSpotifyWidgetActivity.getSwitchSpotifySUserStatus() == 1) {
            SpotifySUserSwitch.setChecked(true);
        } else {
            SpotifySUserSwitch.setChecked(false);
        }
        if (HomeSpotifyWidgetActivity.getSwitchSpotifPlaylistStatus() == 1) {
            SpotifyPlaylistSwitch.setChecked(true);
        } else {
            SpotifyPlaylistSwitch.setChecked(false);
        }
    }

    public static int getSwitchCineStatus()
    {
        return cinemaStatus;
    }
    public static int getSwitchWeatherStatus()
    {
        return weatherStatus;
    }
    public static int getSwitchCovidStatus()
    {
        return covidStatus;
    }
    public static int getSwitchfacebookStatus()
    {
        return facebookStatus;
    }
    public static int getSwitchPaypalStatus()
    {
        return paypalStatus;
    }
    public static int getSwitchImgurStatus()
    {
        return ImgurStatus;
    }
    public static int getSwitchSpotifyStatus() { return SpotifyStatus; }
    public static int getSwitchSpotifySUserStatus() { return SpotifySUserStatus; }
    public static int getSwitchSpotifySMusicStatus() { return SpotifySMusicStatus; }
    public static int getSwitchSpotifyPlaylistStatus() { return SpotifyPlaylistStatus; }





    //Menu Code
    public void ClickMenu(View view) {
        HomeSpotifyWidgetActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeSpotifyWidgetActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        HomeSpotifyWidgetActivity.redirectActivity(this, HomeSpotifyWidgetActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }




    public void SpotifyMusic(String SpotifyMusic, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"SpotifyMusic\" : \""+ SpotifyMusic +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/SpotifyMusic")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void SpotifyUser(String SpotifyUser, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"SpotifyUser\" : \""+ SpotifyUser +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/SpotifyUser")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void SpotifyPlaylist(String SpotifyPlaylist, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"SpotifyPlaylist\" : \""+ SpotifyPlaylist +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/SpotifyPlaylist")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void SpotifyMusic(String SpotifyMusic){
        SpotifyMusic(SpotifyMusic, new Callback() {

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
                            Toast.makeText(SettingsSpotifyActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsSpotifyActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void SpotifyUser(String SpotifyUser){
        SpotifyUser(SpotifyUser, new Callback() {

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
                            Toast.makeText(SettingsSpotifyActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsSpotifyActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

    public void SpotifyPlaylist(String SpotifyPlaylist){
        SpotifyPlaylist(SpotifyPlaylist, new Callback() {

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
                            Toast.makeText(SettingsSpotifyActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsSpotifyActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }
                    }
                });
            }
        });
    }

}