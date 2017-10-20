package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 12/10/17.
 */

public class TvCustomAdapter extends RecyclerView.Adapter<TvCustomAdapter.TvViewHolder> {
    private ArrayList<TvShow> mTvShow;
    private Context mContext;

    public TvCustomAdapter(Context context, ArrayList<TvShow> tvShows) {
        mContext = context;
        mTvShow = tvShows;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.tv_card_layout, parent, false);
        return new TvViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        TvShow mtvItem = mTvShow.get(position);
        holder.mTitle.setText(mtvItem.getOriginalName());
        holder.mRating.setText(String.valueOf(mtvItem.getVoteAverage()));
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500" + mtvItem.getBackdropPath()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mTvShow.size();
    }

    static class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        TextView mRating;
        View itemView;

        public TvViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.mImageView = itemView.findViewById(R.id.tv_card_thumbnail);
            this.mTitle = itemView.findViewById(R.id.tv_card_name);
            this.mRating = itemView.findViewById(R.id.tv_card_rating);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "CLick", Toast.LENGTH_SHORT).show();
            Log.i("Movie Item Click", "true");
        }
    }
}