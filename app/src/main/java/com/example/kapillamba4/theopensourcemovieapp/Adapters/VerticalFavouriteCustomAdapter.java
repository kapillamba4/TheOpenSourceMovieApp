package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Database.FavouriteEntity;
import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 21/10/17.
 */

public class VerticalFavouriteCustomAdapter extends RecyclerView.Adapter<VerticalFavouriteCustomAdapter.FavouriteViewHolder> {
    private ArrayList<FavouriteEntity> mFavEntities;
    private Context mContext;

    public VerticalFavouriteCustomAdapter(Context context, ArrayList<FavouriteEntity> favouriteEntities) {
        mContext = context;
        mFavEntities = favouriteEntities;
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.favourite_card_layout, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        FavouriteEntity favouriteEntity = mFavEntities.get(position);
        holder.mTitle.setText(favouriteEntity.getName());
//      holder.mRating.setText(String.valueOf(mtvItem.getVoteAverage()));

        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + favouriteEntity.getPosterPath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mOverview.setText(favouriteEntity.getOverview());
        holder.mReleaseDate.setText(favouriteEntity.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mFavEntities.size();
    }

    static class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        // TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        View mItemView;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.favourite_vertical_card_thumbnail);
            mTitle = itemView.findViewById(R.id.favourite_vertical_card_title);
            mOverview = itemView.findViewById(R.id.favourite_vertical_card_overview);
            mReleaseDate = itemView.findViewById(R.id.favourite_vertical_card_release);
        }

        @Override
        public void onClick(View view) {
            Log.i("Tv Item Click", "true");
        }
    }
}