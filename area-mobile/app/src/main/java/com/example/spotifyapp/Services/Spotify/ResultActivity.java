package com.example.spotifyapp.Services.Spotify;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.Spotify.fragments.ArtistFragment;
import com.example.spotifyapp.Services.Spotify.fragments.TrackFragment;


public class ResultActivity extends AppCompatActivity {


    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String resultType = getIntent().getExtras().getString(SearchActivity.ResultTypes.RESULT_TYPE);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        String itemId = getIntent().getExtras().getString(SearchActivity.ResultTypes.ITEM_ID);
        String name = getIntent().getExtras().getString(SearchActivity.ResultTypes.NAME);
        String imageUrl = getIntent().getExtras().getString(SearchActivity.ResultTypes.IMAGE_URL);

        switch (resultType) {
            case SearchActivity.ResultTypes.ARTIST:
                if (savedInstanceState == null) {
                    ArtistFragment artistFragment = ArtistFragment.newInstance(itemId, name, imageUrl);
                    if (findViewById(R.id.frame1) != null) { // check for tablet layout
                        transaction.add(R.id.frame1, artistFragment, "ARTIST");
                    } else {
                        transaction.add(R.id.frame, artistFragment, "ARTIST");
                    }
                    transaction.commit();
                }
                break;
            case SearchActivity.ResultTypes.ALBUM:
                if (savedInstanceState == null) {
                    TrackFragment trackFragment = TrackFragment.newInstance(itemId, imageUrl);
                    transaction.add(R.id.frame, trackFragment);
                    transaction.commit();
                }
                break;
        }
    }
}
