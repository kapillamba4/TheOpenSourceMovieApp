package com.example.kapillamba4.theopensourcemovieapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kapillamba4.theopensourcemovieapp.Adapters.MovieCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Movie;
import com.example.kapillamba4.theopensourcemovieapp.Entities.PopularMovie;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Services.MovieService;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    ArrayList<Movie> mMovies = new ArrayList<>();
    RecyclerView mRecyclerView;
    MovieCustomAdapter mMovieCustomAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        mRecyclerView = view.findViewById(R.id.movie_recycler_view);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(horizontalLayoutManager);

        MovieService movieService = retrofit.create(MovieService.class);
        Call<PopularMovie> popularMovieCall = movieService.getPopularMovies(CONSTANTS.API_KEY, 1, "IN");
        popularMovieCall.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                mMovies = new ArrayList<>(response.body().getResults());
                mMovieCustomAdapter = new MovieCustomAdapter(getContext(), mMovies);
                mMovieCustomAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mMovieCustomAdapter);
            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }
}
