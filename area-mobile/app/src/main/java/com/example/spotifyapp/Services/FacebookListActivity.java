package com.example.spotifyapp.Services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.spotifyapp.Fun.CineActivity;
import com.example.spotifyapp.HomeActivity;
import com.example.spotifyapp.HomeFacebookWidgetActivity;
import com.example.spotifyapp.HomeImgurWidgetActivity;
import com.example.spotifyapp.HomeSpotifyWidgetActivity;
import com.example.spotifyapp.News.CovidActivity;
import com.example.spotifyapp.News.WeatherActivity;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.AdapterFacebook.Adapter;
import com.example.spotifyapp.Services.FacebookModel.FBFreinds;
import com.example.spotifyapp.SettingsActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FacebookListActivity extends AppCompatActivity {

    LoginButton loginButton;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    TextView share;

    String token;


    CallbackManager callbackManager;

    static LinearLayout CinemaL, WeatherL, CovidL, FacebookL,
            PaypalL, ImgurL, SpotifyL;

    static int  cinemaStatus, weatherStatus, covidStatus, facebookStatus, paypalStatus, ImgurStatus, SpotifyStatus;

    AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_list);
        drawerLayout = findViewById(R.id.drawer_layout);

        loginButton = findViewById(R.id.button_fb_LoginL);
        recyclerView = findViewById(R.id.rv_friend_list);
        share = findViewById(R.id.share_btn);


        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FacebookListActivity.this, FacebookShare.class));
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

    //code here


    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    private void setToken(String access_token) {
        token = access_token;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    String json = "{\"accesstoken\" : \""+ AccessToken.getCurrentAccessToken().getToken() + "\"}";
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, json);
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2:8080/Facebook/AccessToken")
                            .post(body)
                            .build();
                    Call call = client.newCall(request);
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        token = response.body().string();
                        AccessToken accessToken = new AccessToken(token, AccessToken.getCurrentAccessToken().getApplicationId(), AccessToken.getCurrentAccessToken().getUserId(), null, null, null, null, null, null, null);

                        //GraphRequest graphRequestFriends = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
                        GraphRequest graphRequestFriends = GraphRequest.newMyFriendsRequest(accessToken, new GraphRequest.GraphJSONArrayCallback() {
                            @Override
                            public void onCompleted(JSONArray objects, GraphResponse response) {
                                Log.d("Demo", objects.toString());
                                ArrayList<FBFreinds> fbFreinds = new ArrayList<>();
                                for (int i = 0; i < objects.length(); i++) {
                                    try {
                                        JSONObject object = objects.getJSONObject(i);
                                        fbFreinds.add(new FBFreinds(object.getString("id"), object.getString("name")));
                                    }  catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    layoutManager = new LinearLayoutManager(FacebookListActivity.this);
                                    recyclerView.setLayoutManager(layoutManager);

                                    adapter = new Adapter(fbFreinds);
                                    recyclerView.setAdapter(adapter);
                                }
                            }
                        });
                        graphRequestFriends.executeAsync();

                        //GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String name = object.getString("name");
                                    String id = object.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Bundle bundle = new Bundle();
                        bundle.putString("fields", "email, gender, picture");
                        graphRequest.setParameters(bundle);
                        graphRequest.executeAsync();

                        accessTokenTracker = new AccessTokenTracker() {
                            @Override
                            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                                if (currentAccessToken == null) {
                                    adapter.clear();
                                    LoginManager.getInstance().logOut();
                                }

                            }
                        };
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();





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

    public void ClickImgur(View view){
        HomeActivity.redirectActivity(this, HomeImgurWidgetActivity.class);
    }
    public void ClickSpotify(View view){ HomeActivity.redirectActivity(this, HomeSpotifyWidgetActivity.class);
    }

    public void ClickFacebook(View view) {
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