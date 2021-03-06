package com.example.spotifyapp.Services.Spotify.database;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "playlistWithTracks")

public class PlaylistWithTracks implements Serializable {
    @Embedded
    public Playlist playlist;

    @Relation(
            parentColumn = "playlistName",
            entityColumn = "belongToPlaylistName"
    )
    public List<Track> tracks;
}




