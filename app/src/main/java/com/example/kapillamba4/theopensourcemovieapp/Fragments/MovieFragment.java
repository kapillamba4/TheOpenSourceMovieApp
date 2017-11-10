package com.example.kapillamba4.theopensourcemovieapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.kapillamba4.theopensourcemovieapp.Adapters.HorizontalMovieCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Movie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperMovie;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment  {
    NestedScrollView mNestedScrollView;
    ProgressBar mProgressBar;
    ArrayList<Movie> mPopularMovies = new ArrayList<>();
    ArrayList<Movie> mTopRatedMovies = new ArrayList<>();
    ArrayList<Movie> mUpcomingMovies = new ArrayList<>();
    Retrofit mBuilder;
    private boolean loading  = false;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setupPopularMovies(View view) {
        final RecyclerView mRecyclerView = view.findViewById(R.id.movie_popular_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalLayoutManager.canScrollHorizontally();
        mRecyclerView.setLayoutManager(horizontalLayoutManager);

        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);

        MovieService movieService = mBuilder.create(MovieService.class);
        Call<WrapperMovie> popularMovieCall = movieService.getPopularMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<WrapperMovie>() {
            @Override
            public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mPopularMovies = new ArrayList<>(response.body().getResults());
                final HorizontalMovieCustomAdapter mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mPopularMovies);
                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
            }

            @Override
            public void onFailure(Call<WrapperMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setupTopRatedMovies(View view) {
        final RecyclerView mRecyclerView = view.findViewById(R.id.movie_top_rated_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        horizontalLayoutManager.canScrollHorizontally();
        mRecyclerView.setLayoutManager(horizontalLayoutManager);

        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);

        MovieService movieService = mBuilder.create(MovieService.class);
        Call<WrapperMovie> popularMovieCall = movieService.getTopRatedMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<WrapperMovie>() {
            @Override
            public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mTopRatedMovies = new ArrayList<>(response.body().getResults());
                HorizontalMovieCustomAdapter mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mTopRatedMovies);
                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
            }

            @Override
            public void onFailure(Call<WrapperMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setupUpcomingMovies(View view) {
        final RecyclerView mRecyclerView = view.findViewById(R.id.movie_upcoming_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        horizontalLayoutManager.canScrollHorizontally();
        mRecyclerView.setLayoutManager(horizontalLayoutManager);

        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);

        MovieService movieService = mBuilder.create(MovieService.class);
        Call<WrapperMovie> popularMovieCall = movieService.getUpcomingMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<WrapperMovie>() {
            @Override
            public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mUpcomingMovies = new ArrayList<>(response.body().getResults());
                final HorizontalMovieCustomAdapter mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mUpcomingMovies);
                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                        //Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                        int visibleItemCount = 0, totalItemCount = 0, pastVisiblesItems = 0;
                        if (dx > 0 && !loading) {
                            visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                            totalItemCount = recyclerView.getLayoutManager().getItemCount();
                            pastVisiblesItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = true;
                                Log.v("Scroll: ", "Last Item reached !");
                                MovieService movieService = mBuilder.create(MovieService.class);
                                Call<WrapperMovie> popularMovieCall = movieService.getUpcomingMovies(CONSTANTS.API_KEY, 2, "IN");
                                popularMovieCall.enqueue(new Callback<WrapperMovie>() {
                                    @Override
                                    public void onResponse(Call<WrapperMovie> call, Response<WrapperMovie> response) {
                                        mUpcomingMovies.addAll(response.body().getResults());
                                        mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                                        loading = false;
                                    }

                                    @Override
                                    public void onFailure(Call<WrapperMovie> call, Throwable t) {

                                    }
                                });
                                //Do pagination.. i.e. fetch new data
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<WrapperMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mNestedScrollView = view.findViewById(R.id.movie_scroll_view);
        mProgressBar = view.findViewById(R.id.movie_progress_bar);

        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setupPopularMovies(view);
        setupTopRatedMovies(view);
        setupUpcomingMovies(view);

        return view;
    }
}
