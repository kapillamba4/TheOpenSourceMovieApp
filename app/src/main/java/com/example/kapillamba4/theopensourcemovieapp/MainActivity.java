package com.example.kapillamba4.theopensourcemovieapp;

import android.support.annotation.IdRes;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.kapillamba4.theopensourcemovieapp.Adapters.TvCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private NestedScrollView mScrollView;
    private RecyclerView mRecyclerView;
    private TvCustomAdapter mAdapter;
    private ArrayList<TvShow> mTvShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        mAdapter = new TvCustomAdapter(this, mTvShows);

        mScrollView = (NestedScrollView) findViewById(R.id.scroll_view);
        mBottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.menu_movies:
                        // TODO
                        break;
                    case R.id.menu_personal_favourites:
//                        LayoutInflater inflater = (LayoutInflater) getSystemService(MainActivity.this.LAYOUT_INFLATER_SERVICE);
//                        View view = inflater.inflate(R.layout.tv, null);
//                        mScrollView.addView(view);
                        break;
                    case R.id.menu_tv_shows:
                        // TODO
                        break;
                }
            }
        });
    }
}
