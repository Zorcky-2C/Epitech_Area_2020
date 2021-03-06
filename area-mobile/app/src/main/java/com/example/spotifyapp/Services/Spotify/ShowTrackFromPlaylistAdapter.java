package com.example.spotifyapp.Services.Spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.Spotify.database.Track;

import java.util.ArrayList;

public class ShowTrackFromPlaylistAdapter extends RecyclerView.Adapter<ShowTrackFromPlaylistAdapter.ShowTracksViewHolder> {


    private Context context;
    ArrayList<Track> listOfTracks;

    public ShowTrackFromPlaylistAdapter(ArrayList<Track> listOfTracks,Context context) {
        this.context = context;
        this.listOfTracks = listOfTracks;
    }

    @NonNull
    @Override
    public ShowTracksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row_item, parent, false);
        TextView textView = view.findViewById(R.id.findMusicResult);
        TextView typeView = view.findViewById(R.id.resultType);
        typeView.setText("");
        ImageView imageView = view.findViewById(R.id.findMusicImage);
        final ShowTracksViewHolder viewHolder = new ShowTracksViewHolder(listOfTracks, typeView,view, textView,imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowTracksViewHolder holder, int position) {
        holder.getTextView().setText(listOfTracks.get(position).getTrackName());

        String imageUrl = listOfTracks.get(position).getAlbumImageUrl();
        Glide.with(holder.getContext()).clear(holder.getImageView());
        Glide.with(holder.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.music_note)
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return listOfTracks.size();
    }


    public static class ShowTracksViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textView;
        private View frameLayout;
        private Context context;
        private ImageView imageView;
        ArrayList<Track> listOfTracks;


        public ShowTracksViewHolder(ArrayList<Track> listOfTracks,@NonNull final View itemView, View frameLayout, TextView textView, ImageView imageView) {
            super(frameLayout);
            this.textView = textView;
            this.imageView=imageView;
            this.listOfTracks = listOfTracks;
            context = itemView.getContext();
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public Context getContext() {
            return context;
        }

    }

}
