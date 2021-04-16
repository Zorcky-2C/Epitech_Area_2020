package com.example.spotifyapp.Services;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifyapp.R;
import com.example.spotifyapp.RegisterActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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

public class FacebookProfilActivity extends AppCompatActivity {

    // Declaring Variables.
    CallbackManager callbackManager;
    TextView FacebookName, FacebookEmail, FacebookId, FacebookGender, FacebookLink, FacebookTime, FacebookLocal, FacebookUpdate, FacebookVerif;
    LoginButton loginButton;
    AccessTokenTracker accessTokenTracker;
    String acstoken;
    String logtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Passing MainActivity in Facebook SDK.
        FacebookSdk.sdkInitialize(FacebookProfilActivity.this);

        // Adding Callback Manager.
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook_profil);

        // Assign TextView ID.
        FacebookName = findViewById(R.id.username);
        FacebookEmail = findViewById(R.id.email);
        FacebookGender = findViewById(R.id.gender);
        FacebookLink = findViewById(R.id.link);
        FacebookTime = findViewById(R.id.timezone);
        FacebookLocal = findViewById(R.id.local);
        FacebookUpdate = findViewById(R.id.update);
        FacebookVerif = findViewById(R.id.verif);
        FacebookId = findViewById(R.id.id);

        // Assign Facebook Login button ID.
        loginButton = (LoginButton)findViewById(R.id.login_button);

        // Giving permission to Login Button.
        loginButton.setReadPermissions("email");

        if (AccessToken.getCurrentAccessToken() != null)
            LoginWithOkHttp();

        // Checking the Access Token.


        // Adding Click listener to Facebook login button.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            String json = "{\"accesstoken\" : \""+ loginResult.getAccessToken().getToken() + "\"}";
                            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                            RequestBody body = RequestBody.create(JSON, json);
                            Request request = new Request.Builder()
                                    .url("http://10.0.2.2:8080/Facebook/AccessToken")
                                    .post(body)
                                    .build();
                            Call call = client.newCall(request);
                            Response response = call.execute();
                            if (response.isSuccessful()) {
                                logtoken = response.body().string();
                                AccessToken accessToken = new AccessToken(logtoken, AccessToken.getCurrentAccessToken().getApplicationId(), AccessToken.getCurrentAccessToken().getUserId(), null, null, null, null, null, null, null);
                                //GraphLoginRequest(loginResult.getAccessToken().getToken());
                                GraphLoginRequest(accessToken);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                // Calling method to access User Data After successfully login.
            }

            @Override
            public void onCancel(){

                Toast.makeText(FacebookProfilActivity.this,"Login Canceled",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception){

                Toast.makeText(FacebookProfilActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
            }

        });

        // Detect user is login or not. If logout then clear the TextView and delete all the user info from TextView.
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
                if (accessToken2 == null) {

                    // Clear the TextView after logout.
                    FacebookEmail.setText("");
                    FacebookName.setText("");
                    FacebookLink.setText("");
                    FacebookGender.setText("");
                    FacebookTime.setText("");
                    FacebookLocal.setText("");
                    FacebookVerif.setText("");
                    FacebookUpdate.setText("");
                    FacebookId.setText("");

                }
            }
        };
    }

    public void LoginWithOkHttp(Callback callback){
        OkHttpClient client = new OkHttpClient();
        String json = "{\"accesstoken\" : \""+ AccessToken.getCurrentAccessToken().getToken() + "\"}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/Facebook/AccessToken")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void LoginWithOkHttp(){
        LoginWithOkHttp(new Callback() {

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
                            try {
                                acstoken = response.body().string();
                                if(acstoken!=null){
                                    AccessToken accessToken = new AccessToken(acstoken, AccessToken.getCurrentAccessToken().getApplicationId(), AccessToken.getCurrentAccessToken().getUserId(), null, null, null, null, null, null, null);

                                    //GraphLoginRequest(AccessToken.getCurrentAccessToken());
                                    GraphLoginRequest(accessToken);

                                    // If already login in then show the Toast.
                                    Toast.makeText(FacebookProfilActivity.this,"Already logged in",Toast.LENGTH_SHORT).show();

                                }else {

                                    // If not login in then show the Toast.
                                    Toast.makeText(FacebookProfilActivity.this,"User not logged in",Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.d("App: ", "Login failed");
                            Log.d("Response Code: ", String.valueOf(response.code()));
                            Log.d("Response Code Message: ", response.message());
                            Log.d("Response Status: ", String.valueOf(response.isSuccessful()));
                        }
                    }
                });
            }
        });
    }

    // Method to access Facebook User Data.
    protected void GraphLoginRequest(AccessToken accessToken){
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

                        try {

                            // Adding all user info one by one into TextView.
                            FacebookId.setText("ID: " + jsonObject.getString("id"));

                            FacebookName.setText(FacebookName.getText() + "First name : " + jsonObject.getString("first_name"));

                            FacebookName.setText(FacebookName.getText() + " " + jsonObject.getString("last_name"));

                            FacebookEmail.setText(FacebookEmail.getText() + "\nEmail : " + jsonObject.getString("email"));

                            FacebookGender.setText(FacebookGender.getText() + "\nGender : " + jsonObject.getString("gender"));

                            FacebookLink.setText(FacebookLink.getText() + "\nLink : " + jsonObject.getString("link"));

                            FacebookTime.setText(FacebookTime.getText() + "\nTime zone : " + jsonObject.getString("timezone"));

                            FacebookLocal.setText(FacebookLocal.getText() + "\nLocale : " + jsonObject.getString("locale"));

                            FacebookTime.setText(FacebookTime.getText() + "\nUpdated time : " + jsonObject.getString("updated_time"));

                            FacebookVerif.setText(FacebookVerif.getText() + "\nVerified : " + jsonObject.getString("verified"));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle bundle = new Bundle();
        bundle.putString(
                "fields",
                "id,name,link,email,gender,last_name,first_name,locale,timezone,updated_time,verified"
        );
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(FacebookProfilActivity.this);

    }
}