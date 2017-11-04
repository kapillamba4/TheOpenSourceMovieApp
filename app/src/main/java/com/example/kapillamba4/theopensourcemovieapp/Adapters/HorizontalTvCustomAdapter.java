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
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 12/10/17.
 */

public class HorizontalTvCustomAdapter extends RecyclerView.Adapter<HorizontalTvCustomAdapter.TvViewHolder> {
    private ArrayList<TvShow> mTvShow;
    private Context mContext;
    private onClickCustomListener mOnItemClickListener;

    public HorizontalTvCustomAdapter(Context context, ArrayList<TvShow> tvShows) {
        mContext = context;
        mTvShow = tvShows;
        try {
            mOnItemClickListener = (onClickCustomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickedListener");
        }
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.tv_card_layout, parent, false);
        final TvViewHolder tvViewHolder = new TvViewHolder(inflatedView);
        tvViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvViewHolder.id != null && tvViewHolder.id != "") {
                    mOnItemClickListener.onItemClick(tvViewHolder.id, "tv");
                }
            }
        });
        return tvViewHolder;
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        TvShow mtvItem = mTvShow.get(position);
        holder.mTitle.setText(mtvItem.getName());
        holder.mRating.setText(String.valueOf(mtvItem.getVoteAverage()));
        holder.id = String.valueOf(mtvItem.getId());
        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + mtvItem.getPosterPath()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mTvShow.size();
    }

    public interface onClickCustomListener {
        void onItemClick(String id, String type);
    }

    static class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        TextView mRating;
        String id;
        View mItemView;

        public TvViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            id = "";
            mImageView = itemView.findViewById(R.id.tv_card_thumbnail);
            mTitle = itemView.findViewById(R.id.tv_card_name);
            mRating = itemView.findViewById(R.id.tv_card_rating);
        }

        @Override
        public void onClick(View view) {
            Log.i("Tv Item Click", "true");
        }
    }
}