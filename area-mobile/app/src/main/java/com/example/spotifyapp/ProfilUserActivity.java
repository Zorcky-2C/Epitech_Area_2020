package com.example.spotifyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilUserActivity extends AppCompatActivity {

    RelativeLayout profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user);

        profil = findViewById(R.id.profileclick);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilUserActivity.this, UserActivity.class));
            }
        });
    }
}