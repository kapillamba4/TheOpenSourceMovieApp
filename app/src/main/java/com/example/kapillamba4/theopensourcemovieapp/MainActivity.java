package com.example.kapillamba4.theopensourcemovieapp;

import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.kapillamba4.theopensourcemovieapp.Fragments.MovieFragment;
import com.example.kapillamba4.theopensourcemovieapp.Fragments.TvFragment;
import com.example.kapillamba4.theopensourcemovieapp.Network.NetworkChangeReceiver;


public class MainActivity extends AppCompatActivity {

    private static TextView mCheckConnection;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private BottomNavigationView mBottomNavigationView;
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

    public static void dialog(boolean value) {
        if (value) {
            mCheckConnection.setText("We are back !!!");
            mCheckConnection.setBackgroundColor(Color.GREEN);
            mCheckConnection.setTextColor(Color.WHITE);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    mCheckConnection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);
        } else {
            mCheckConnection.setVisibility(View.VISIBLE);
            mCheckConnection.setText("Could not Connect to internet");
            mCheckConnection.setBackgroundColor(Color.RED);
            mCheckConnection.setTextColor(Color.WHITE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mCheckConnection = (TextView) findViewById(R.id.check_connection);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();
    }

    private void registerNetworkBroadcastForNougat() {
        registerReceiver(mNetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkChangeReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}
