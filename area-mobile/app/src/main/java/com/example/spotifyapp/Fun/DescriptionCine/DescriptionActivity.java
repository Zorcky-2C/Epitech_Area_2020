package com.example.spotifyapp.Fun.DescriptionCine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotifyapp.R;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {
    ImageView imageView;
    TextView name;
    TextView description;
    TextView rate;

    String textDescription;
    String textRate;
    String playername;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        imageView=findViewById(R.id.image);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        rate = findViewById(R.id.rate);

        playername=getIntent().getStringExtra("name");
        textDescription=getIntent().getStringExtra("description");
        textRate=getIntent().getStringExtra("rate");
        image=getIntent().getStringExtra("image");
        name.setText(playername);
        description.setText(textDescription);
        rate.setText(textRate);


        Picasso.with(DescriptionActivity.this).load(image).into(imageView);
    }

}