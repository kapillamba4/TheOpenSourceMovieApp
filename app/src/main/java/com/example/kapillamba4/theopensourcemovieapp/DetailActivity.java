package com.example.kapillamba4.theopensourcemovieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Entities.DetailMovie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.DetailTv;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Genre;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Services.TvService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private String id, type;
    private ImageView mCover;
    private View mBlackMask;
    private NestedScrollView mNestedScrollView;
    private ProgressBar mProgressBar;
    private FloatingActionButton mFavFab;
    private TextView mTitle, mStatus, mReleaseDate, mGenre, mOverview;
    private DetailTv mDetailTv;
    private DetailMovie mDetailMovie;
    private ArrayList<String> mGenres = new ArrayList<>();
    private Retrofit mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mCover = findViewById(R.id.cover);
        mBlackMask = findViewById(R.id.black_mask);
        mNestedScrollView = findViewById(R.id.details);
        mFavFab = findViewById(R.id.favourites_fab);
        mTitle = findViewById(R.id.title);
        mStatus = findViewById(R.id.status);
        mReleaseDate = findViewById(R.id.release_date);
        mGenre = findViewById(R.id.genre);
        mOverview = findViewById(R.id.overview);
       // mProgressBar = findViewById(R.id.progress_bar);
        FloatingActionButton fab = findViewById(R.id.favourites_fab);
        fab.setOnClickListener(this);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        switch (type) {
            case "tv":
                TvService tvService = mBuilder.create(TvService.class);
                Call<DetailTv> detailTvCall = tvService.getDetailTvShow(id, CONSTANTS.API_KEY);
                detailTvCall.enqueue(new Callback<DetailTv>() {
                    @Override
                    public void onResponse(Call<DetailTv> call, Response<DetailTv> response) {
                        mDetailTv = response.body();
                        for (Genre genre : mDetailTv.getGenres()) {
                            mGenres.add(genre.getName());
                        }

                        mGenre.setText("| ");
                        Picasso.with(DetailActivity.this).load(CONSTANTS.BASE_POSTER_URL_LARGE + mDetailTv.getPosterPath()).into(mCover);
                        for (String genre : mGenres) {
                            mGenre.setText(mGenre.getText() + genre + " | ");
                        }

                        mTitle.setText(mDetailTv.getName());
                        mOverview.setText(mDetailTv.getOverview());
                        mStatus.setText(mDetailTv.getStatus());
                        mReleaseDate.setText(" " + mDetailTv.getFirstAirDate());
//                      mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<DetailTv> call, Throwable t) {
                        Log.i("Api Call: ", "Failure");
                    }
                });

                break;
            case "movie":
                MovieService movieService = mBuilder.create(MovieService.class);
                Call<DetailMovie> detailMovieCall = movieService.getDetailMovie(id, CONSTANTS.API_KEY);
                detailMovieCall.enqueue(new Callback<DetailMovie>() {
                    @Override
                    public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                        mDetailMovie = response.body();
                        for (Genre genre : mDetailMovie.getGenres()) {
                            mGenres.add(genre.getName());
                        }

                        mGenre.setText("| ");
                        Picasso.with(DetailActivity.this).load(CONSTANTS.BASE_POSTER_URL_LARGE + mDetailMovie.getPosterPath()).into(mCover);
                        for (String genre : mGenres) {
                            mGenre.setText(mGenre.getText() + genre + " | ");
                        }

                        mTitle.setText(mDetailMovie.getTitle());
                        mOverview.setText(mDetailMovie.getOverview());
                        mStatus.setText(mDetailMovie.getStatus());
                        mReleaseDate.setText(mDetailMovie.getReleaseDate());
//                      mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<DetailMovie> call, Throwable t) {
                        Log.i("Api Call: ", "Failure");
                    }
                });

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favourites_fab:
                Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}
