package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
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
    private int mLayoutId;
    private String mBaseImageUrl;

    public ImageAdapter(Context context, ArrayList<String> urls, @LayoutRes int id, String baseImageUrl) {
        mImages = urls;
        mContext = context;
        mLayoutId = id;
        mBaseImageUrl = baseImageUrl;
    }

    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new ImageViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
        Picasso.with(mContext).load(mBaseImageUrl+mImages.get(position)).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        View mItemView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
