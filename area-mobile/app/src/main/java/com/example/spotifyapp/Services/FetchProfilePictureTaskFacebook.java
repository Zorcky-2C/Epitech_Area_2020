package com.example.spotifyapp.Services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FetchProfilePictureTaskFacebook extends AsyncTask<Uri, Void, Bitmap> {
    private ImageView imageView;

    public FetchProfilePictureTaskFacebook(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(Uri... urls) {
        Bitmap img = null;
        try {
            InputStream in = new URL(urls[0].toString()).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (IOException e) { Log.e("fetch_profile_pic", Log.getStackTraceString(e)); }

        return img;
    }

    protected void onPostExecute(Bitmap result) {
        if(result != null) imageView.setImageBitmap(result);
    }
}