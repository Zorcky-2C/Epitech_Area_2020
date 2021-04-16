package com.example.spotifyapp.News;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
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

public class WeatherActivity extends AppCompatActivity {

    TextView view_city;
    TextView view_temp;
    TextView view_desc;

    ImageView view_weather;
    EditText search;
    FloatingActionButton search_floating;

    DrawerLayout drawerLayout;


    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    static int  cinemaStatus, weatherStatus,
              covidStatus, facebookStatus, paypalStatus, ImgurStatus, SpotifyStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        drawerLayout = findViewById(R.id.drawer_layout);

        view_city=findViewById(R.id.town);
        view_city.setText("");
        view_temp=findViewById(R.id.temp);
        view_temp.setText("");
        view_desc=findViewById(R.id.desc);
        view_desc.setText("");

        view_weather=findViewById(R.id.wheather_image);
        search=findViewById(R.id.search_edit);
        search_floating=findViewById(R.id.floating_search);

        search_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide Keyboard
                InputMethodManager imm=(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getRootView().getWindowToken(),0);
                api_key(String.valueOf(search.getText()));
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
    }



    private void api_key(final String City) {
        //String url = "https://api.openweathermap.org/data/2.5/weather?q="+City+"&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric";
        String url = "http://10.0.2.2:8080/Weather/getWeather";
        OkHttpClient client=new OkHttpClient();
        String json = "{\"city\" : \""+ City + "\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Response response= client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String responseData= response.body().string();
                    try {
                        JSONObject json=new JSONObject(responseData);
                        JSONArray array=json.getJSONArray("weather");
                        JSONObject object=array.getJSONObject(0);

                        String description=object.getString("description");
                        String icons = object.getString("icon");

                        JSONObject temp1= json.getJSONObject("main");
                        Double Temperature=temp1.getDouble("temp");

                        setText(view_city,City);

                        String temps= Math.round(Temperature)+" °C";
                        setText(view_temp,temps);
                        setText(view_desc,description);
                        setImage(view_weather,icons);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void setText(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
    private void setImage(final ImageView imageView, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //paste switch
                switch (value){
                    case "01d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "02d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "03d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "04d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "09d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "10d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "11d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "13d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.wheather));

                }
            }
        });
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
        recreate();
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