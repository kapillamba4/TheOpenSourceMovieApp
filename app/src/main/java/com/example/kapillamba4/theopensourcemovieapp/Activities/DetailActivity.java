package com.example.kapillamba4.theopensourcemovieapp.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amitshekhar.DebugDB;
import com.example.kapillamba4.theopensourcemovieapp.Adapters.CastAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Adapters.ImageAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Adapters.VideoAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Database.FavouriteDatabase;
import com.example.kapillamba4.theopensourcemovieapp.Database.FavouriteEntityDao;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Cast;
import com.example.kapillamba4.theopensourcemovieapp.Entities.DetailMovie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.DetailTv;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Genre;
import com.example.kapillamba4.theopensourcemovieapp.Entities.ResourceImage;
import com.example.kapillamba4.theopensourcemovieapp.Entities.ResourceVideo;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperCast;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperImage;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperVideo;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.CommonService;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Services.TvService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONTRACT;
import com.example.kapillamba4.theopensourcemovieapp.Utils.HELPERS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, VideoAdapter.onClickCustomListener {
    private String id, type;
    private ImageView mCover;
    private View mBlackMask;
    private NestedScrollView mNestedScrollView;
    private ProgressBar mProgressBar;
    private FloatingActionButton mFavFab;
    private TextView mTitle, mStatus, mReleaseDate, mGenre, mOverview;
    private DetailTv mDetailTv;
    private DetailMovie mDetailMovie;
    private ArrayList<ResourceImage> mPosters = new ArrayList<>();
    private ArrayList<ResourceImage> mBackdrops = new ArrayList<>();
    private ArrayList<ResourceVideo> mVideos = new ArrayList<>();
    private ArrayList<Cast> mCast = new ArrayList<>();
    private RecyclerView posterRecyclerView;
    private RecyclerView backdropRecyclerView;
    private RecyclerView videoRecyclerView;
    private RecyclerView castRecyclerView;
    private LinearLayoutManager horizontalLayoutManager;

    //    private ArrayList<String> mCastNames = new ArrayList<>();
    private ArrayList<String> mGenres = new ArrayList<>();
    private Retrofit mBuilder;
    private FavouriteDatabase db;
    private Boolean backdropsExpanded, postersExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdropsExpanded = false;
        postersExpanded = false;
        mCover = findViewById(R.id.cover);
        mBlackMask = findViewById(R.id.black_mask);
        mNestedScrollView = findViewById(R.id.details);
        mFavFab = findViewById(R.id.favourites_fab);
        mTitle = findViewById(R.id.title);
        mStatus = findViewById(R.id.status);
        mReleaseDate = findViewById(R.id.release_date);
        mGenre = findViewById(R.id.genre);
        mOverview = findViewById(R.id.overview);
        posterRecyclerView = findViewById(R.id.posters);
        backdropRecyclerView = findViewById(R.id.backdrops);
        videoRecyclerView = findViewById(R.id.trailers);
        castRecyclerView = findViewById(R.id.cast);

        (horizontalLayoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false)).canScrollHorizontally();
        posterRecyclerView.setLayoutManager(horizontalLayoutManager);
        (horizontalLayoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false)).canScrollHorizontally();
        backdropRecyclerView.setLayoutManager(horizontalLayoutManager);
        (horizontalLayoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false)).canScrollHorizontally();
        videoRecyclerView.setLayoutManager(horizontalLayoutManager);
        (horizontalLayoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false)).canScrollHorizontally();
        castRecyclerView.setLayoutManager(horizontalLayoutManager);

        // mProgressBar = findViewById(R.id.progress_bar);
        FloatingActionButton fab = findViewById(R.id.favourites_fab);
        fab.setOnClickListener(this);

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        db = Room.databaseBuilder(this, FavouriteDatabase.class, CONTRACT.FAVOURTIES_DBNAME).build();

        switch (type) {
            case "tv":
                TvService tvService = mBuilder.create(TvService.class);
                Call<DetailTv> detailTvCall = tvService.getDetailTvShow(id, CONSTANTS.MOVIE_DB_API_KEY);
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

                        CommonService commonService = mBuilder.create(CommonService.class);
                        Call<WrapperImage> wrapperImageCall = commonService.getImages(type, mDetailTv.getId().toString(), CONSTANTS.MOVIE_DB_API_KEY);
                        Call<WrapperVideo> wrapperVideoCall = commonService.getVideos(type, mDetailTv.getId().toString(), CONSTANTS.MOVIE_DB_API_KEY);
                        Call<WrapperCast> wrapperCastCall = commonService.getCastMovie(type, mDetailTv.getId().toString(), CONSTANTS.MOVIE_DB_API_KEY);

                        wrapperImageCall.enqueue(new Callback<WrapperImage>() {
                            @Override
                            public void onResponse(Call<WrapperImage> call, Response<WrapperImage> response) {
                                mPosters = new ArrayList<>(response.body().getPosters());
                                mBackdrops = new ArrayList<>(response.body().getBackdrops());

                                ArrayList<String> posterUrls = new ArrayList<>();
                                ArrayList<String> backdropUrls = new ArrayList<>();
                                for (ResourceImage image : mPosters) {
                                    posterUrls.add(image.getFilePath());
                                }

                                for (ResourceImage image : mBackdrops) {
                                    backdropUrls.add(image.getFilePath());
                                }

                                ImageAdapter posterAdapter = new ImageAdapter(DetailActivity.this, posterUrls);
                                ImageAdapter backdropAdapter = new ImageAdapter(DetailActivity.this, backdropUrls);
                                posterRecyclerView.setAdapter(posterAdapter);
                                backdropRecyclerView.setAdapter(backdropAdapter);
                                posterAdapter.notifyDataSetChanged();
                                backdropAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<WrapperImage> call, Throwable t) {

                            }
                        });


                        wrapperVideoCall.enqueue(new Callback<WrapperVideo>() {
                            @Override
                            public void onResponse(Call<WrapperVideo> call, Response<WrapperVideo> response) {
                                mVideos = new ArrayList<>(response.body().getResults());

                                ArrayList<String> trailerUrls = new ArrayList<>();
                                for (ResourceVideo video : mVideos) {
                                    trailerUrls.add(video.getKey());
                                }

                                VideoAdapter videoAdapter = new VideoAdapter(DetailActivity.this, trailerUrls);
                                videoRecyclerView.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<WrapperVideo> call, Throwable t) {

                            }
                        });

                        mTitle.setText(mDetailTv.getName());
                        mOverview.setText(mDetailTv.getOverview());
                        mStatus.setText(mDetailTv.getStatus());
                        mReleaseDate.setText(" " + mDetailTv.getFirstAirDate());
                        wrapperCastCall.enqueue(new Callback<WrapperCast>() {
                            @Override
                            public void onResponse(Call<WrapperCast> call, Response<WrapperCast> response) {
                                mCast = new ArrayList<>(response.body().getCast());
                                CastAdapter castAdapter = new CastAdapter(DetailActivity.this, mCast);
                                castRecyclerView.setAdapter(castAdapter);
                                castAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<WrapperCast> call, Throwable t) {

                            }
                        });

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
                Call<DetailMovie> detailMovieCall = movieService.getDetailMovie(id, CONSTANTS.MOVIE_DB_API_KEY);

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

                        CommonService commonService = mBuilder.create(CommonService.class);
                        Call<WrapperImage> wrapperImageCall = commonService.getImages(type, mDetailMovie.getId().toString(), CONSTANTS.MOVIE_DB_API_KEY);
                        Call<WrapperVideo> wrapperVideoCall = commonService.getVideos(type, mDetailMovie.getId().toString(), CONSTANTS.MOVIE_DB_API_KEY);
                        Call<WrapperCast> wrapperCastCall = commonService.getCastMovie(type, mDetailMovie.getId().toString(), CONSTANTS.MOVIE_DB_API_KEY);

                        wrapperImageCall.enqueue(new Callback<WrapperImage>() {
                            @Override
                            public void onResponse(Call<WrapperImage> call, Response<WrapperImage> response) {
                                mPosters = new ArrayList<>(response.body().getPosters());
                                mBackdrops = new ArrayList<>(response.body().getBackdrops());
                                ArrayList<String> posterUrls = new ArrayList<>();
                                ArrayList<String> backdropUrls = new ArrayList<>();
                                for (ResourceImage image : mPosters) {
                                    posterUrls.add(image.getFilePath());
                                }

                                for (ResourceImage image : mBackdrops) {
                                    backdropUrls.add(image.getFilePath());
                                }

                                ImageAdapter posterAdapter = new ImageAdapter(DetailActivity.this, posterUrls);
                                ImageAdapter backdropAdapter = new ImageAdapter(DetailActivity.this, backdropUrls);
                                posterRecyclerView.setAdapter(posterAdapter);
                                backdropRecyclerView.setAdapter(backdropAdapter);
                                posterAdapter.notifyDataSetChanged();
                                backdropAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<WrapperImage> call, Throwable t) {

                            }
                        });


                        wrapperVideoCall.enqueue(new Callback<WrapperVideo>() {
                            @Override
                            public void onResponse(Call<WrapperVideo> call, Response<WrapperVideo> response) {
                                mVideos = new ArrayList<>(response.body().getResults());

                                ArrayList<String> trailerUrls = new ArrayList<>();
                                for (ResourceVideo video : mVideos) {
                                    trailerUrls.add(video.getKey());
                                }

                                VideoAdapter videoAdapter = new VideoAdapter(DetailActivity.this, trailerUrls);
                                videoRecyclerView.setAdapter(videoAdapter);
                                videoAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<WrapperVideo> call, Throwable t) {

                            }
                        });

                        mTitle.setText(mDetailMovie.getTitle());
                        mOverview.setText(mDetailMovie.getOverview());
                        mStatus.setText(mDetailMovie.getStatus());
                        mReleaseDate.setText(" " + mDetailMovie.getReleaseDate());
                        wrapperCastCall.enqueue(new Callback<WrapperCast>() {
                            @Override
                            public void onResponse(Call<WrapperCast> call, Response<WrapperCast> response) {
                                mCast = new ArrayList<>(response.body().getCast());
                                CastAdapter castAdapter = new CastAdapter(DetailActivity.this, mCast);
                                castRecyclerView.setAdapter(castAdapter);
                                castAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<WrapperCast> call, Throwable t) {

                            }
                        });

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
        // TODO: Deal with memory leak

        switch (view.getId()) {
            case R.id.favourites_fab:
                switch (type) {
                    case "tv":
                        new AsyncTask<DetailTv, Void, Void>() {
                            @Override
                            protected Void doInBackground(DetailTv... detailTvs) {
                                db.favouriteEntityDao().insert(HELPERS.convert(detailTvs[0]));
                                return null;
                            }
                        }.execute(mDetailTv);
                        Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).setAction("REMOVE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new AsyncTask<DetailTv, Void, Void>() {
                                    @Override
                                    protected Void doInBackground(DetailTv... detailTvs) {
                                        db.favouriteEntityDao().delete(HELPERS.convert(detailTvs[0]));
                                        return null;
                                    }
                                }.execute(mDetailTv);
                            }
                        }).show();
                        break;
                    case "movie":
                        new AsyncTask<DetailMovie, Void, Void>() {
                            @Override
                            protected Void doInBackground(DetailMovie... detailMovies) {
                                db.favouriteEntityDao().insert(HELPERS.convert(detailMovies[0]));
                                return null;
                            }
                        }.execute(mDetailMovie);

                        Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).setAction("REMOVE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new AsyncTask<DetailMovie, Void, Void>() {
                                    @Override
                                    protected Void doInBackground(DetailMovie... detailMovies) {
                                        db.favouriteEntityDao().delete(HELPERS.convert(detailMovies[0]));
                                        return null;
                                    }
                                }.execute(mDetailMovie);
                            }
                        }).show();
                        break;
                }
                break;
        }
    }

    @Override
    public void onItemClick(String url, String type) {
        switch (type) {
            case "video":
                if (url != null && url != "") {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }

                break;
            case "image":
                break;
        }
    }
}
