package com.example.kapillamba4.theopensourcemovieapp.Fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Adapters.HorizontalMovieCustomAdapter;
import com.example.kapillamba4.theopensourcemovieapp.R;

public class DetailFragment extends Fragment  {
    private String id;
    private ImageView mCover;
    private View mBlackMask;
    private NestedScrollView mNestedScrollView;
    private FloatingActionButton mFavFab;
    private TextView mTitle, mStatus, mReleaseDate, mGenre, mTagline, mOverview;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mCover = view.findViewById(R.id.cover);
        mBlackMask = view.findViewById(R.id.black_mask);
        mNestedScrollView = view.findViewById(R.id.details);
        mFavFab = view.findViewById(R.id.favourites_fab);
        mTitle = view.findViewById(R.id.title);
        mStatus = view.findViewById(R.id.status);
        mReleaseDate = view.findViewById(R.id.release_date);
        mGenre = view.findViewById(R.id.genre);
        mOverview = view.findViewById(R.id.overview);
        mTagline = view.findViewById(R.id.tagline);

        getArguments().get("id");
        getArguments().get("type");
        return view;
    }
}
