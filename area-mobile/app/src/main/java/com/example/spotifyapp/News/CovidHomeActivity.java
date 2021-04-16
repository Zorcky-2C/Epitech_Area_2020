package com.example.spotifyapp.News;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.spotifyapp.Fun.CineActivity;
import com.example.spotifyapp.HomeActivity;
import com.example.spotifyapp.HomeFacebookWidgetActivity;
import com.example.spotifyapp.HomeImgurWidgetActivity;
import com.example.spotifyapp.HomeSpotifyWidgetActivity;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.PaypalActivity;
import com.example.spotifyapp.SettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidHomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    TextView cases, todayCases, deaths, todayDeaths, recoved, active, critical, affectedCountries;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    static int  cinemaStatus, weatherStatus,
            covidStatus, facebookStatus, paypalStatus, ImgurStatus, SpotifyStatus;

    String url = "http://10.0.2.2:8080/Covid/getCovidGlobalInfo";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_home);


        drawerLayout = findViewById(R.id.drawer_layout);

        cases = findViewById(R.id.tvCase);
        todayCases = findViewById(R.id.tv_TodayCases);
        deaths = findViewById(R.id.tvDeaths);
        todayDeaths = findViewById(R.id.tvTodaysDeaths);
        recoved = findViewById(R.id.tvRecovered);
        active = findViewById(R.id.tvActive);
        critical = findViewById(R.id.tvCritical);
        affectedCountries = findViewById(R.id.tvAffectedCountries);

        getGeneralCovidInfo(url);

        //setting setup

        CinemaL = findViewById(R.id.cineLayout);
        WeatherL = findViewById(R.id.weatherLayout);
        CovidL = findViewById(R.id.covidLayout);
        FacebookL = findViewById(R.id.facebookLayout);
        PaypalL = findViewById(R.id.paypalLayout);
        ImgurL = findViewById(R.id.imgurLayout);
        SpotifyL = findViewById(R.id.spotifyLayout);
        setVisibilityWidget();
    }

    public void getGeneralCovidInfo(String url, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void getGeneralCovidInfo(String url){
        getGeneralCovidInfo(url, new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d("Error: ", " no responses");
                Log.d("Error message: ", e.getMessage());
                call.cancel();
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final boolean ResponseCode = response.isSuccessful();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (ResponseCode == true){
                            try {
                                JSONObject obj = new JSONObject(response.body().string());
                                String stCase = String.valueOf(obj.getLong("cases"));
                                String sttodayCases = String.valueOf(obj.getLong("todayCases"));
                                String stDeaths = String.valueOf(obj.getLong("deaths"));
                                String stTodasDeath = String.valueOf(obj.getLong("todayDeaths"));
                                String stRecocered = String.valueOf(obj.getLong("recovered"));
                                String stActive = String.valueOf(obj.getLong("active"));
                                String stCritical = String.valueOf(obj.getLong("critical"));
                                String stCountires = String.valueOf(obj.getLong("affectedCountries"));

                                cases.setText(stCase);
                                todayCases.setText(sttodayCases);
                                deaths.setText(stDeaths);
                                todayDeaths.setText(stTodasDeath);
                                recoved.setText(stRecocered);
                                active.setText(stActive);
                                critical.setText(stCritical);
                                affectedCountries.setText(stCountires);

                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("Log: ", "getGeneralCovidInfo is successful.");
                        }else{
                            Log.d("App: ", "getGeneralCovidInfo failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                            try {
                                Log.d("Response Body", response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
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
        recreate();
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