package com.example.kapillamba4.theopensourcemovieapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.kapillamba4.theopensourcemovieapp.Fragments.MovieFragment;
import com.example.kapillamba4.theopensourcemovieapp.Fragments.TvFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem tab) {
            switch (tab.getItemId()) {
                case R.id.menu_movies:
                    MovieFragment movieFragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, movieFragment).commit();
                    break;
                case R.id.menu_tv_shows:
                    TvFragment tvFragment = new TvFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, tvFragment).commit();
                    break;
                case R.id.menu_personal_favourites:
                    // TODO
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
