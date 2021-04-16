package com.example.spotifyapp.Services.FacebookAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.spotifyapp.R;
import com.example.spotifyapp.Services.FacebookModel.AndroidVersionFacebook;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersionFacebook> android;

    public DataAdapter(ArrayList<AndroidVersionFacebook> android) {
        this.android = android;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row_facebook, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(android.get(i).getName());
        viewHolder.tv_version.setText(android.get(i).getVer());
        viewHolder.tv_api_level.setText(android.get(i).getApi());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_version = (TextView)view.findViewById(R.id.tv_version);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }

}
