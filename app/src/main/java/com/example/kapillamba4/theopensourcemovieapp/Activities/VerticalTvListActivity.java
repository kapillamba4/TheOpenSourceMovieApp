package com.example.kapillamba4.theopensourcemovieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kapillamba4.theopensourcemovieapp.Adapters.VerticalMovieCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Adapters.VerticalTvCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Movie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperMovie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperTvShow;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Services.TvService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerticalTvListActivity extends AppCompatActivity {

    private Retrofit mBuilder;
    private RecyclerView mRecyclerView;
    private ArrayList<TvShow> mTvShows;
    private String ListHeader;
    private Call<WrapperTvShow> wrapperTvCall;
    private VerticalTvCustomAdapter verticalTvCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_tv_list);
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRecyclerView = findViewById(R.id.tv_vertical_recycler_view);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        verticalLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(verticalLayoutManager);
        handleIntent(getIntent());
        wrapperTvCall.enqueue(new Callback<WrapperTvShow>() {
            @Override
            public void onResponse(Call<WrapperTvShow> call, Response<WrapperTvShow> response) {
                mTvShows = new ArrayList<>(response.body().getResults());
                verticalTvCustomAdapter = new VerticalTvCustomAdapter(VerticalTvListActivity.this, mTvShows);
                mRecyclerView.setAdapter(verticalTvCustomAdapter);
                verticalTvCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<WrapperTvShow> call, Throwable t) {

            }
        });
    }

    private void handleIntent(Intent intent) {
        ListHeader = intent.getStringExtra("header");
        TvService tvService = mBuilder.create(TvService.class);
        switch (ListHeader) {
            case CONSTANTS.UPCOMING_TAG:
                wrapperTvCall = tvService.getAiringTvShows(CONSTANTS.MOVIE_DB_API_KEY, 1);
                break;
            case CONSTANTS.POPULAR_TAG:
                wrapperTvCall = tvService.getPopularTVShows(CONSTANTS.MOVIE_DB_API_KEY, 1);
                break;
            case CONSTANTS.TOP_RATED_TAG:
                wrapperTvCall = tvService.getTopRatedTvShows(CONSTANTS.MOVIE_DB_API_KEY, 1);
                break;
            default:
                finish();
        }
    }
}
