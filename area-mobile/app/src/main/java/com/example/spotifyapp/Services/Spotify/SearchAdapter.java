package com.example.spotifyapp.Services.Spotify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.Spotify.spotify.model.Image;
import com.example.spotifyapp.Services.Spotify.spotify.model.Item;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private SearchActivity.SearchItem[] dummyData;
    private List<Item> results;
    private Context context;

    public SearchAdapter(Context context, List<Item> results) {
        this.context = context;
        this.results = results;
    }

    public SearchAdapter(SearchActivity.SearchItem[] dummyData) {
        this.dummyData = dummyData;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row_item, parent, false);
        TextView resultView = view.findViewById(R.id.findMusicResult);
        TextView typeView = view.findViewById(R.id.resultType);
        ImageView imageView = view.findViewById(R.id.findMusicImage);
        final SearchViewHolder viewHolder = new SearchViewHolder(view, resultView, typeView, imageView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.getResultView().setText(results.get(position).getName());
        holder.getTypeView().setText(results.get(position).getType().toUpperCase());
        holder.setItemId(results.get(position).getId());
        holder.setExternalTrackUrl(results.get(position).getExternalUrls().getSpotify());
        holder.setOpenInAppTrackUrl(results.get(position).getUri());
        List<Image> images = results.get(position).getImages();

        if(results.get(position).getType().toUpperCase().equals(SearchActivity.ResultTypes.TRACK)) {
            images = results.get(position).getAlbum().getImages();
        }

        if (images != null && images.size() > 0) {
            String imageUrl = images.get(0).getUrl();
            holder.setImageUrl(imageUrl);
            Glide.with(holder.getContext()).clear(holder.getImageView());
            Glide.with(holder.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.music_note)
                    .into(holder.getImageView());
        } else {
            holder.getImageView().setImageResource(R.drawable.music_note);
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView resultView;
        private TextView typeView;
        private ImageView imageView;
        private Context context;
        private String itemId;
        private String imageUrl = "";
        private String externalTrackUrl;
        private String openInAppTrackUrl;

        public SearchViewHolder(@NonNull final View itemView, TextView resultView, TextView typeView, ImageView imageView, Context context) {
            super(itemView);
            this.resultView = resultView;
            this.typeView = typeView;
            this.imageView = imageView;
            this.context = context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToResultActivity();
                }
            });
        }

        public TextView getResultView() {
            return resultView;
        }

        public TextView getTypeView() {
            return typeView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public Context getContext() {
            return context;
        }

        public void setItemId(String id) {
            this.itemId = id;
        }

        public void setImageUrl(String url) {
            this.imageUrl = url;
        }

        public String getExternalTrackUrl() {
            return externalTrackUrl;
        }

        public void setExternalTrackUrl(String externalTrackUrl) {
            this.externalTrackUrl = externalTrackUrl;
        }

        public String getOpenInAppTrackUrl() {
            return openInAppTrackUrl;
        }

        public void setOpenInAppTrackUrl(String openInAppTrackUrl) {
            this.openInAppTrackUrl = openInAppTrackUrl;
        }

        private void goToResultActivity() {
            Intent intent = new Intent(context, ResultActivity.class);
            intent.putExtra(SearchActivity.ResultTypes.RESULT_TYPE, typeView.getText().toString());
            intent.putExtra(SearchActivity.ResultTypes.ITEM_ID, itemId);
            intent.putExtra(SearchActivity.ResultTypes.NAME, resultView.getText().toString());
            intent.putExtra(SearchActivity.ResultTypes.IMAGE_URL, imageUrl);
            intent.putExtra(SearchActivity.ResultTypes.EXTERNAL_TRACK_URL, externalTrackUrl);
            intent.putExtra(SearchActivity.ResultTypes.OPEN_IN_SPOTIFY_URL, openInAppTrackUrl);
            context.startActivity(intent);
        }
    }
}

