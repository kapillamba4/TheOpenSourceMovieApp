package com.example.kapillamba4.theopensourcemovieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MovieDetailActivity extends AppCompatActivity implements FloatingActionButton.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);


       FloatingActionButton fab = findViewById(R.id.favourites_fab);
       fab.setOnClickListener(this);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
    }

    @Override
    public void onClick(View view) {

    }
}
