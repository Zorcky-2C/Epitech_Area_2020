package com.example.spotifyapp.Services.Spotify;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.spotifyapp.Services.Spotify.database.AppDatabase;
import com.example.spotifyapp.Services.Spotify.database.Playlist;
import com.example.spotifyapp.Services.Spotify.database.PlaylistWithTracks;
import com.example.spotifyapp.Services.Spotify.database.Track;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DbRepository {

    private String DB_NAME = "db_spotify";

    private AppDatabase database;

    public DbRepository(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }


    public void insertPlaylist(String playlistName) {
        Playlist playlist = new Playlist();

        playlist.setPlaylistName(playlistName);
        insertPlaylist(playlist);
    }

    public void insertPlaylist(final Playlist playlist) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    database.playlistDAO().insertPlaylist(playlist);
                    return null;
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    public void insertTrack(String trackId,
                            String trackName, String externalTrackUrl, String openInAppTrackUrl, String albumImageUrl
            ,String belongToPlaylistName) {
        Track track = new Track();

        track.setTrackId(trackId);
        track.setTrackName(trackName);
        track.setExternalTrackUrl(externalTrackUrl);
        track.setOpenInAppTrackUrl(openInAppTrackUrl);
        track.setAlbumImageUrl(albumImageUrl);
        track.setBelongToPlaylistName(belongToPlaylistName);

        insertTrack(track);
    }

    public void insertTrack(final Track track) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    database.trackDAO().insertTrack(track);
                    return null;
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<PlaylistWithTracks> getTrackWithPlaylists() {
        return database.playlistDAO().getTrackWithPlaylists();
    }

}
