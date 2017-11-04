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
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 17/10/17.
 */

public class HorizontalMovieCustomAdapter extends RecyclerView.Adapter<HorizontalMovieCustomAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies;
    private Context mContext;
    private onClickCustomListener mOnItemClickListener;

    public HorizontalMovieCustomAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
        try {
            mOnItemClickListener = (onClickCustomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickedListener");
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.movie_card_layout, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(inflatedView);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movieViewHolder.id != null && movieViewHolder.id != "") {
                    mOnItemClickListener.onItemClick(movieViewHolder.id, "movie");
                }
            }
        });

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(HorizontalMovieCustomAdapter.MovieViewHolder holder, int position) {
        Movie mMovie = mMovies.get(position);
        holder.mTitle.setText(mMovie.getTitle());
        holder.mRating.setText(String.valueOf(mMovie.getVoteAverage()));
        holder.id = String.valueOf(mMovie.getId());
        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + mMovie.getPosterPath()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public interface onClickCustomListener {
        void onItemClick(String id, String type);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        TextView mRating;
        View mItemView;
        String id;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            id = "";
            mImageView = itemView.findViewById(R.id.movie_card_thumbnail);
            mTitle = itemView.findViewById(R.id.movie_card_name);
            mRating = itemView.findViewById(R.id.movie_card_rating);
        }

        @Override
        public void onClick(View view) {
            Log.i("Movie Item Click", "true");
        }
    }
}
