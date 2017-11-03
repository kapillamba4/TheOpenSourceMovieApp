package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kapillamba4.theopensourcemovieapp.Entities.Movie;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 21/10/17.
 */

public class VerticalMovieCustomAdapter extends RecyclerView.Adapter<VerticalMovieCustomAdapter.MovieViewHolder> {

    private ArrayList<Movie> mMovies;
    private Context mContext;

    public VerticalMovieCustomAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_card_vertical_list, parent, false);
        return new VerticalMovieCustomAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VerticalMovieCustomAdapter.MovieViewHolder holder, int position) {
        Movie mMovie = mMovies.get(position);
        holder.mTitle.setText(mMovie.getTitle());
//        holder.mRating.setText(String.valueOf(mtvItem.getVoteAverage()));
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + mMovie.getPosterPath()).into(holder.mImageView);
        holder.mOverview.setText(mMovie.getOverview());
        holder.mReleaseDate.setText(mMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        // TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        View mItemView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.movie_vertical_card_thumbnail);
            mTitle = itemView.findViewById(R.id.movie_vertical_card_title);
            mOverview = itemView.findViewById(R.id.movie_vertical_card_overview);
            mReleaseDate = itemView.findViewById(R.id.movie_vertical_card_release);
        }

        @Override
        public void onClick(View view) {
            Log.i("Tv Item Click", "true");
        }
    }
}