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

public class SettingsImgurActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    static Switch ImgurFavSwitch, ImgurFeedSwitch, ImgurProfileSwitch;
    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
    SpotifyStatus, ImgurFavStatus, ImgurFeedStatus, ImgurProfilStatus;

    static LinearLayout CinemaL, WeatherL,
            InfosL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_widget_imgur);

        ImgurFavSwitch = findViewById(R.id.ImgurFavSwitch);
        ImgurFeedSwitch = findViewById(R.id.ImgurFeedSwitch);
        ImgurProfileSwitch = findViewById(R.id.ImgurProfileSwitch);
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
        if (SettingsImgurActivity.getSwitchCineStatus() == 1) {
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchWeatherStatus() == 1) {
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchfacebookStatus() == 1) {
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchPaypalStatus() == 1) {
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchImgurStatus() == 1) {
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchSpotifyStatus() == 1) {
            SpotifyL.setVisibility(View.VISIBLE);
            SpotifyStatus = 1;
        } else {
            SpotifyL.setVisibility(View.GONE);
            SpotifyStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchImgurFavStatus() == 1) {
            ImgurFavStatus = 1;
        } else {
            ImgurFavStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchImgurFeedStatus() == 1) {
            ImgurFeedStatus= 1;
        } else {
            ImgurFeedStatus = 0;
        }
        if (SettingsImgurActivity.getSwitchImgurProfileStatus() == 1) {
            ImgurProfilStatus = 1;
        } else {
            ImgurProfilStatus = 0;
        }
    }

    public void setVisibilityWidgetModel()
    {
        ImgurFavSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImgurFavStatus = 1;
                } else {
                    // The toggle is disabled
                    ImgurFavStatus = 0;
                }
                Log.d("ImgurFavorite : ", String.valueOf(ImgurFavStatus));
                ImgurFavorite(String.valueOf(ImgurFavStatus));
            }
        });
        ImgurFeedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImgurFeedStatus = 1;
                } else {
                    // The toggle is disabled
                    ImgurFeedStatus = 0;
                }
                Log.d("ImgurFeed : ", String.valueOf(ImgurFeedStatus));
                ImgurFeed(String.valueOf(ImgurFeedStatus));
            }
        });
        ImgurProfileSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ImgurProfilStatus = 1;
                } else {
                    // The toggle is disabled
                    ImgurProfilStatus = 0;
                }
                Log.d("ImgurProfil : ", String.valueOf(ImgurProfilStatus));
                ImgurProfil(String.valueOf(ImgurProfilStatus));
            }
        });
    }

    public void getSwitchStatus()
    {
        if (HomeImgurWidgetActivity.getSwitchImgurFavStatus() == 1) {
            ImgurFavSwitch.setChecked(true);
        } else {
            ImgurFavSwitch.setChecked(false);
        }
        if (HomeImgurWidgetActivity.getSwitchImgurFeedStatus() == 1) {
            ImgurFeedSwitch.setChecked(true);
        } else {
            ImgurFeedSwitch.setChecked(false);
        }
        if (HomeImgurWidgetActivity.getSwitchImgurProfileStatus() == 1) {
            ImgurProfileSwitch.setChecked(true);
        } else {
            ImgurProfileSwitch.setChecked(false);
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
    public static int getSwitchImgurProfileStatus() { return ImgurProfilStatus; }
    public static int getSwitchImgurFavStatus() { return ImgurFavStatus; }
    public static int getSwitchImgurFeedStatus() { return ImgurFeedStatus; }


    //Menu Code
    public void ClickMenu(View view) {
        HomeImgurWidgetActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeImgurWidgetActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        HomeImgurWidgetActivity.redirectActivity(this, HomeImgurWidgetActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }


    public void ImgurFavorite(String ImgurFavorite, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"ImgurFavorite\" : \""+ ImgurFavorite +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ImgurFavorite")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void ImgurFeed(String ImgurFeed, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"ImgurFeed\" : \""+ ImgurFeed +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ImgurFeed")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void ImgurProfil(String ImgurProfil, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"ImgurProfil\" : \""+ ImgurProfil +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/ImgurProfil")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void ImgurFavorite(String ImgurFavorite){
        ImgurFavorite(ImgurFavorite, new Callback() {

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
                            Toast.makeText(SettingsImgurActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsImgurActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
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

    public void ImgurFeed(String ImgurFeed){
        ImgurFeed(ImgurFeed, new Callback() {

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
                            Toast.makeText(SettingsImgurActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsImgurActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
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

    public void ImgurProfil(String ImgurProfil){
        ImgurProfil(ImgurProfil, new Callback() {

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
                            Toast.makeText(SettingsImgurActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsImgurActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
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