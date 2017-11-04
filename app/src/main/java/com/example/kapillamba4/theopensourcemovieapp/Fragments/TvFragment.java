package com.example.kapillamba4.theopensourcemovieapp.Fragments;


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

import com.example.kapillamba4.theopensourcemovieapp.Adapters.HorizontalTvCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperTvShow;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.TvService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvFragment extends Fragment {
    ProgressBar mProgressBar;
    NestedScrollView mNestedScrollView;
    ArrayList<TvShow> mPopularTvShows = new ArrayList<>();
    ArrayList<TvShow> mTopRatedTvShows = new ArrayList<>();
    ArrayList<TvShow> mUpcomingTvShows = new ArrayList<>();
    Retrofit mBuilder;

    public TvFragment() {
        // Required empty public constructor
    }

    public void setupPopularTvShows(View view) {
        final RecyclerView mRecyclerView = view.findViewById(R.id.tv_popular_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);
        TvService tvService = mBuilder.create(TvService.class);
        Call<WrapperTvShow> popularTvCall = tvService.getPopularTVShows(CONSTANTS.API_KEY, 1);
        popularTvCall.enqueue(new Callback<WrapperTvShow>() {
            @Override
            public void onResponse(Call<WrapperTvShow> call, Response<WrapperTvShow> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mPopularTvShows = new ArrayList<>(response.body().getResults());
                HorizontalTvCustomAdapter mHorizontalTvCustomAdapter = new HorizontalTvCustomAdapter(getContext(), mPopularTvShows);
                mHorizontalTvCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalTvCustomAdapter);
            }

            @Override
            public void onFailure(Call<WrapperTvShow> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setupTopRatedTvShows(View view) {
        final RecyclerView mRecyclerView = view.findViewById(R.id.tv_top_rated_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);
        TvService tvService = mBuilder.create(TvService.class);
        Call<WrapperTvShow> popularTvCall = tvService.getTopRatedTvShows(CONSTANTS.API_KEY, 1);
        popularTvCall.enqueue(new Callback<WrapperTvShow>() {
            @Override
            public void onResponse(Call<WrapperTvShow> call, Response<WrapperTvShow> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mPopularTvShows = new ArrayList<>(response.body().getResults());
                HorizontalTvCustomAdapter mHorizontalTvCustomAdapter = new HorizontalTvCustomAdapter(getContext(), mPopularTvShows);
                mHorizontalTvCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalTvCustomAdapter);
            }

            @Override
            public void onFailure(Call<WrapperTvShow> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void setupUpcomingTvShows(View view) {
        final RecyclerView mRecyclerView = view.findViewById(R.id.tv_upcoming_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        SnapHelper mSnapHelper = new LinearSnapHelper();
        mSnapHelper.attachToRecyclerView(mRecyclerView);
        TvService tvService = mBuilder.create(TvService.class);
        Call<WrapperTvShow> popularTvCall = tvService.getPopularTVShows(CONSTANTS.API_KEY, 1);
        popularTvCall.enqueue(new Callback<WrapperTvShow>() {
            @Override
            public void onResponse(Call<WrapperTvShow> call, Response<WrapperTvShow> response) {
                mProgressBar.setVisibility(View.GONE);
                mNestedScrollView.setVisibility(View.VISIBLE);
                mUpcomingTvShows = new ArrayList<>(response.body().getResults());
                HorizontalTvCustomAdapter mHorizontalTvCustomAdapter = new HorizontalTvCustomAdapter(getContext(), mUpcomingTvShows);
                mHorizontalTvCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mHorizontalTvCustomAdapter);
            }

            @Override
            public void onFailure(Call<WrapperTvShow> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        mNestedScrollView = view.findViewById(R.id.tv_scroll_view);
        mProgressBar = view.findViewById(R.id.tv_progress_bar);
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setupPopularTvShows(view);
        setupTopRatedTvShows(view);
        setupUpcomingTvShows(view);

        return view;
    }
}
