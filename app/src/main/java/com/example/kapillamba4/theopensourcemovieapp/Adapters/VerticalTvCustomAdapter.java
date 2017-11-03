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
 * Created by kapil on 21/10/17.
 */

public class VerticalTvCustomAdapter extends RecyclerView.Adapter<VerticalTvCustomAdapter.TvViewHolder> {

    private ArrayList<TvShow> mTvShow;
    private Context mContext;

    public VerticalTvCustomAdapter(Context context, ArrayList<TvShow> tvShows) {
        mContext = context;
        mTvShow = tvShows;
    }

    @Override
    public VerticalTvCustomAdapter.TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_card_vertical_list, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VerticalTvCustomAdapter.TvViewHolder holder, int position) {
        TvShow mtvItem = mTvShow.get(position);
        holder.mTitle.setText(mtvItem.getName());
//        holder.mRating.setText(String.valueOf(mtvItem.getVoteAverage()));
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185" + mtvItem.getPosterPath()).into(holder.mImageView);
        holder.mOverview.setText(mtvItem.getOverview());
        holder.mReleaseDate.setText(mtvItem.getFirstAirDate());
    }

    @Override
    public int getItemCount() {
        return mTvShow.size();
    }

    static class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        View mItemView;

        public TvViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.tv_vertical_card_thumbnail);
            mTitle = itemView.findViewById(R.id.tv_vertical_card_title);
            mOverview = itemView.findViewById(R.id.tv_vertical_card_overview);
            mReleaseDate = itemView.findViewById(R.id.tv_vertical_card_release);
        }

        @Override
        public void onClick(View view) {
            Log.i("Tv Item Click", "true");
        }
    }
}
