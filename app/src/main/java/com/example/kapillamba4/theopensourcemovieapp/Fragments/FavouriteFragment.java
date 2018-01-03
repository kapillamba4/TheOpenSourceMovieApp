package com.example.kapillamba4.theopensourcemovieapp.Fragments;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
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
import com.example.kapillamba4.theopensourcemovieapp.Adapters.VerticalFavouriteCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Database.FavouriteDatabase;
import com.example.kapillamba4.theopensourcemovieapp.Database.FavouriteEntity;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Movie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.WrapperMovie;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONTRACT;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouriteFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ArrayList<FavouriteEntity> mFavouriteEntities;

    private Retrofit mBuilder;
    private boolean loading  = false;
    private FavouriteDatabase db;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setupFavourites(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
//        mProgressBar = view.findViewById(R.id._progress_bar);
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRecyclerView = view.findViewById(R.id.favourite_vertical_recycler_view);

        db = Room.databaseBuilder(getContext(), FavouriteDatabase.class, CONTRACT.FAVOURTIES_DBNAME).allowMainThreadQueries().build();
        mFavouriteEntities = new ArrayList<>(db.favouriteEntityDao().getAll());
        VerticalFavouriteCustomAdapter mVerticalFavouriteCustomAdapter = new VerticalFavouriteCustomAdapter(getContext(), mFavouriteEntities);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        verticalLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(verticalLayoutManager);
        mRecyclerView.setAdapter(mVerticalFavouriteCustomAdapter);
        mVerticalFavouriteCustomAdapter.notifyDataSetChanged();
        return view;
    }
}
