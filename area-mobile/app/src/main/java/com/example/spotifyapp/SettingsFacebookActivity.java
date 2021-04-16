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

public class SettingsFacebookActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    static Switch FacebookProfileSwitch, FacebookPostSwitch, FacebookListFSwitch;
    static int cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus,
    SpotifyStatus, FacebookListFStatus, FacebookProfilStatus, FacebookPostStatus;

    static LinearLayout CinemaL, WeatherL,
            InfosL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    ImageView Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_widget_faecbook);

        FacebookListFSwitch = findViewById(R.id.FacebookListFSwitch);
        FacebookPostSwitch = findViewById(R.id.FacebookPostSwitch);
        FacebookProfileSwitch = findViewById(R.id.FacebookProfileSwitch);
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
        if (SettingsFacebookActivity.getSwitchCineStatus() == 1) {
            CinemaL.setVisibility(View.VISIBLE);
            cinemaStatus = 1;
        } else {
            CinemaL.setVisibility(View.GONE);
            cinemaStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchWeatherStatus() == 1) {
            WeatherL.setVisibility(View.VISIBLE);
            weatherStatus = 1;
        } else {
            WeatherL.setVisibility(View.GONE);
            weatherStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchfacebookStatus() == 1) {
            FacebookL.setVisibility(View.VISIBLE);
            facebookStatus = 1;
        } else {
            FacebookL.setVisibility(View.GONE);
            facebookStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchPaypalStatus() == 1) {
            PaypalL.setVisibility(View.VISIBLE);
            paypalStatus = 1;
        } else {
            PaypalL.setVisibility(View.GONE);
            paypalStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchImgurStatus() == 1) {
            ImgurL.setVisibility(View.VISIBLE);
            ImgurStatus = 1;
        } else {
            ImgurL.setVisibility(View.GONE);
            ImgurStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchSpotifyStatus() == 1) {
            SpotifyL.setVisibility(View.VISIBLE);
            SpotifyStatus = 1;
        } else {
            SpotifyL.setVisibility(View.GONE);
            SpotifyStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchFacebookListFStatus() == 1) {
            FacebookListFStatus = 1;
        } else {
            FacebookListFStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitcFacebookProfileStatus() == 1) {
            FacebookProfilStatus= 1;
        } else {
            FacebookProfilStatus = 0;
        }
        if (SettingsFacebookActivity.getSwitchFacebookPostStatus() == 1) {
            FacebookPostStatus = 1;
        } else {
            FacebookPostStatus = 0;
        }
    }

    public void setVisibilityWidgetModel()
    {
        FacebookListFSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FacebookListFStatus = 1;
                } else {
                    // The toggle is disabled
                    FacebookListFStatus = 0;
                }
                Log.d("FacebookList : ", String.valueOf(FacebookListFStatus));
                FacebookList(String.valueOf(FacebookListFStatus));
            }
        });
        FacebookProfileSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FacebookProfilStatus = 1;
                } else {
                    // The toggle is disabled
                    FacebookProfilStatus = 0;
                }
                Log.d("FacebookProfil : ", String.valueOf(FacebookProfilStatus));
                FacebookProfil(String.valueOf(FacebookProfilStatus));
            }
        });
        FacebookPostSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FacebookPostStatus = 1;
                } else {
                    // The toggle is disabled
                    FacebookPostStatus = 0;
                }
                Log.d("FacebookPost : ", String.valueOf(FacebookPostStatus));
                FacebookPost(String.valueOf(FacebookPostStatus));
            }
        });
    }

    public void getSwitchStatus()
    {
        if (HomeFacebookWidgetActivity.getSwitchFacebookPostStatus() == 1) {
            FacebookPostSwitch.setChecked(true);
        } else {
            FacebookPostSwitch.setChecked(false);
        }
        if (HomeFacebookWidgetActivity.getSwitchFacebookProfileStatus() == 1) {
            FacebookProfileSwitch.setChecked(true);
        } else {
            FacebookProfileSwitch.setChecked(false);
        }
        if (HomeFacebookWidgetActivity.getSwitchFacebookListFStatus() == 1) {
            FacebookListFSwitch.setChecked(true);
        } else {
            FacebookListFSwitch.setChecked(false);
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
    public static int getSwitcFacebookProfileStatus() { return FacebookProfilStatus; }
    public static int getSwitchFacebookPostStatus() { return FacebookPostStatus; }
    public static int getSwitchFacebookListFStatus() { return FacebookListFStatus; }





    //Menu Code
    public void ClickMenu(View view) {
        HomeImgurWidgetActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeImgurWidgetActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        HomeImgurWidgetActivity.redirectActivity(this, HomeFacebookWidgetActivity.class);
    }

    @Override
    protected void onPause(){
        super.onPause();
        HomeActivity.closeDrawer(drawerLayout);
    }




    public void FacebookList(String FacebookList, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"FacebookList\" : \""+ FacebookList +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/FacebookList")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void FacebookProfil(String FacebookProfil, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"FacebookProfil\" : \""+ FacebookProfil +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/FacebookProfil")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void FacebookPost(String FacebookPost, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String json = "{\"FacebookPost\" : \""+ FacebookPost +"\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/accounts/FacebookPost")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void FacebookList(String FacebookList){
        FacebookList(FacebookList, new Callback() {

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
                            Toast.makeText(SettingsFacebookActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsFacebookActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
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

    public void FacebookProfil(String FacebookProfil){
        FacebookProfil(FacebookProfil, new Callback() {

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
                            Toast.makeText(SettingsFacebookActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsFacebookActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
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

    public void FacebookPost(String FacebookPost){
        FacebookPost(FacebookPost, new Callback() {

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
                            Toast.makeText(SettingsFacebookActivity.this,"setting is successful", Toast.LENGTH_SHORT).show();
                            Log.d("Log: ", "setting is successful.");
                            try {
                                Log.d("Response body: ", response.body().string());
                            } catch (IOException e) {}
                        }else{
                            Toast.makeText(SettingsFacebookActivity.this,"setting failed", Toast.LENGTH_SHORT).show();
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