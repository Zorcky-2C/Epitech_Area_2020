package com.example.spotifyapp.Services.AdapterFacebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.FacebookModel.FBFreinds;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private static ArrayList<FBFreinds> fbFreinds;

    public Adapter(ArrayList<FBFreinds> fbFreinds) {
        this.fbFreinds = fbFreinds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_facebook_friends_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(fbFreinds.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return fbFreinds.size();
    }

    public void clear() {
        fbFreinds.clear();
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_friend_pic);
            textView = itemView.findViewById(R.id.tv_friend_name);
        }
    }
}
