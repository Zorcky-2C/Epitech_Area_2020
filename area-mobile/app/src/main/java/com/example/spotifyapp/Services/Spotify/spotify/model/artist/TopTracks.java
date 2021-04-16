package com.example.spotifyapp.Services.Spotify.spotify.model.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopTracks {

    @SerializedName("tracks")
    @Expose
    private List<Track> tracks = null;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

}
