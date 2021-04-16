package com.example.spotifyapp.Services.FacebookModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("android/jsonandroid")
    Call<JSONResponseFacebook> getJSON();
}
