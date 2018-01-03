package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 10/11/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private ArrayList<String> mImages;
    private Context mContext;

    public ImageAdapter(Context context, ArrayList<String> urls) {
        mImages = urls;
        mContext = context;
    }

    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.image, parent, false);
        return new ImageViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
        holder.mProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_LARGE + mImages.get(position)).resize(0, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        View mItemView;
        View mProgressBar;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.image);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
