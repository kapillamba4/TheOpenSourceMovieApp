package com.example.kapillamba4.theopensourcemovieapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.kapillamba4.theopensourcemovieapp.Adapters.HorizontalMovieCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Movie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularMovie;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    NestedScrollView mNestedScrollView;
    ProgressBar mProgressBar;
    ArrayList<Movie> mPopularMovies = new ArrayList<>();
    ArrayList<Movie> mTopRatedMovies = new ArrayList<>();
    ArrayList<Movie> mUpcomingMovies = new ArrayList<>();
    Retrofit mBuilder;

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
        Call<PopularMovie> popularMovieCall = movieService.getPopularMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mPopularMovies = new ArrayList<>(response.body().getResults());
                HorizontalMovieCustomAdapter mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mPopularMovies);
                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {
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
        Call<PopularMovie> popularMovieCall = movieService.getTopRatedMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mTopRatedMovies = new ArrayList<>(response.body().getResults());
                HorizontalMovieCustomAdapter mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mTopRatedMovies);
                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {
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
        Call<PopularMovie> popularMovieCall = movieService.getUpcomingMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mUpcomingMovies = new ArrayList<>(response.body().getResults());
                HorizontalMovieCustomAdapter mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mUpcomingMovies);
                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {
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
//        mRecyclerView = view.findViewById(R.id.movie_popular_recycler_view);
//        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        horizontalLayoutManager.canScrollHorizontally();
//        mRecyclerView.setLayoutManager(horizontalLayoutManager);
//        // TODO add infinte scrolling
//
//        mSnapHelper = new LinearSnapHelper();
//        mSnapHelper.attachToRecyclerView(mRecyclerView);
//
//        MovieService movieService = mBuilder.create(MovieService.class);
//        Call<PopularMovie> popularMovieCall = movieService.getPopularMovies(CONSTANTS.API_KEY, 1, "IN");
//        popularMovieCall.enqueue(new Callback<PopularMovie>() {
//            @Override
//            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
//                mProgressBar.setVisibility(View.GONE);
//                mNestedScrollView.setVisibility(View.VISIBLE);
//                mMovies = new ArrayList<>(response.body().getResults());
//                mHorizontalMovieCustomAdapter = new HorizontalMovieCustomAdapter(getContext(), mMovies);
//                mHorizontalMovieCustomAdapter.notifyDataSetChanged();
//                mRecyclerView.setAdapter(mHorizontalMovieCustomAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<PopularMovie> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

        return view;
    }
}
