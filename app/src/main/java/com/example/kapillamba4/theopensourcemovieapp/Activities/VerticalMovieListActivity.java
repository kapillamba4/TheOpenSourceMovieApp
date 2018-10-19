package com.kapillamba4.thecompletemovieguide.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kapillamba4.thecompletemovieguide.Adapters.SearchDataAdapter;
import com.kapillamba4.thecompletemovieguide.Adapters.VerticalMovieCustomAdapter;
import com.kapillamba4.thecompletemovieguide.Entities.Movie;
import com.kapillamba4.thecompletemovieguide.Entities.MultiSearch;
import com.kapillamba4.thecompletemovieguide.Entities.WrapperMovie;
import com.kapillamba4.thecompletemovieguide.R;
import com.kapillamba4.thecompletemovieguide.Services.CommonService;
import com.kapillamba4.thecompletemovieguide.Services.MovieService;
import com.kapillamba4.thecompletemovieguide.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerticalMovieListActivity extends AppCompatActivity {

    private Retrofit mBuilder;
    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovies;
    private String ListHeader;
    private Call<WrapperMovie> wrapperMovieCall;
    private VerticalMovieCustomAdapter verticalMovieCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_movie_list);
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRecyclerView = findViewById(R.id.movie_vertical_recycler_view);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        verticalLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(verticalLayoutManager);
        handleIntent(getIntent());
        wrapperMovieCall.enqueue(new Callback<WrapperMovie>() {
            @Override
            public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                mMovies = new ArrayList<>(response.body().getResults());
                verticalMovieCustomAdapter = new VerticalMovieCustomAdapter(VerticalMovieListActivity.this, mMovies);
                mRecyclerView.setAdapter(verticalMovieCustomAdapter);
                verticalMovieCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<WrapperMovie> call, Throwable t) {

            }
        });
    }

    private void handleIntent(Intent intent) {
        ListHeader = intent.getStringExtra("header");
        MovieService movieService = mBuilder.create(MovieService.class);
        switch (ListHeader) {
            case CONSTANTS.UPCOMING_TAG:
                wrapperMovieCall = movieService.getUpcomingMovies(CONSTANTS.MOVIE_DB_API_KEY, 1);
                break;
            case CONSTANTS.POPULAR_TAG:
                wrapperMovieCall = movieService.getPopularMovies(CONSTANTS.MOVIE_DB_API_KEY, 1);
                break;
            case CONSTANTS.TOP_RATED_TAG:
                wrapperMovieCall = movieService.getTopRatedMovies(CONSTANTS.MOVIE_DB_API_KEY, 1);
                break;
            default:
                finish();
        }
    }
}
