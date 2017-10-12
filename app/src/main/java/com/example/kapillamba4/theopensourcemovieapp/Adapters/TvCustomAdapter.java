package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.media.tv.TvView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kapillamba4.theopensourcemovieapp.Entities.TvShow;
import com.example.kapillamba4.theopensourcemovieapp.MainActivity;
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
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.entity, parent, false);
        return new TvViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        TvShow tvItem = mTvShow.get(position);
        Picasso.with(mContext).load(tvItem.getThumbnail()).into(holder.mImageView);
        holder.mTitle.setText(tvItem.getTitle());
        holder.mRelease.setText(tvItem.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mTvShow.size();
    }

    static class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        RatingBar mRatingBar;
        TextView mRelease;
        View itemView;

        public TvViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.mImageView = itemView.findViewById(R.id.thumbnail);
            this.mTitle = itemView.findViewById(R.id.display_name);
            this.mRelease = itemView.findViewById(R.id.release_date);
            this.mRatingBar = itemView.findViewById(R.id.rating_bar);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "CLick", Toast.LENGTH_SHORT).show();
        }
    }
}
