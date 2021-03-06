package com.example.spotifyapp.Services.Spotify.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.Spotify.AlbumAdapter;
import com.example.spotifyapp.Services.Spotify.spotify.model.Albums;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumFragment extends Fragment {
    private static final String Albums = "Albums";
    private static final String ArtistImageUrl = "ArtistImageUrl";

    private RecyclerView recyclerView;
    private ImageView artistImage;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private com.example.spotifyapp.Services.Spotify.spotify.model.Albums album;
    private String artistImageUrl;

    public AlbumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AlbumFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlbumFragment newInstance(Albums albums, String artistImageUrl) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putSerializable(Albums, albums);
        args.putString(ArtistImageUrl, artistImageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            album = (Albums) getArguments().getSerializable(Albums);
            artistImageUrl = getArguments().getString(ArtistImageUrl);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Albums");
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_album, container, false);
        //Image
        artistImage = (ImageView) layout.getViewById(R.id.artistImage3);
        System.out.println(artistImageUrl);

        Glide.with(getActivity()).clear(artistImage);
        Glide.with(getActivity())
                .load(artistImageUrl)
                .placeholder(R.drawable.music_note)
                .into(artistImage);
        //Albums
        recyclerView = (RecyclerView) layout.getViewById(R.id.albumListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AlbumAdapter(album.getItems(), getActivity());
        recyclerView.setAdapter(adapter);

        return layout;
    }
}
