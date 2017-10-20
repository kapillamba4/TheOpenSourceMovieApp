package com.example.kapillamba4.theopensourcemovieapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kapillamba4.theopensourcemovieapp.Adapters.TvCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularTv;
import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
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

    ArrayList<TvShow> mTvShows = new ArrayList<>();
    RecyclerView mRecyclerView;
    TvCustomAdapter mTvCustomAdapter;

    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        mRecyclerView = view.findViewById(R.id.tv_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);

        TvService tvService = retrofit.create(TvService.class);
        Call<PopularTv> popularTvCall = tvService.getPopularTVShows(CONSTANTS.API_KEY, 1);
        popularTvCall.enqueue(new Callback<PopularTv>() {
            @Override
            public void onResponse(Call<PopularTv> call, Response<PopularTv> response) {
                mTvShows = new ArrayList<>(response.body().getResults());
                mTvCustomAdapter = new TvCustomAdapter(getContext(), mTvShows);
                mTvCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mTvCustomAdapter);
            }

            @Override
            public void onFailure(Call<PopularTv> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }
}
