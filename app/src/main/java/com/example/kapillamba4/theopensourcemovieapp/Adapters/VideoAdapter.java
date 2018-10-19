package com.kapillamba4.thecompletemovieguide.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//import com.bumptech.glide.Glide;
import com.kapillamba4.thecompletemovieguide.R;
import com.kapillamba4.thecompletemovieguide.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by kapil on 31/12/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private ArrayList<String> mVideoKeys;
    private Context mContext;
    private onClickCustomListener mOnItemClickListener;

    public VideoAdapter(Context context, ArrayList<String> keys) {
        mVideoKeys = keys;
        mContext = context;
        try {
            mOnItemClickListener = (onClickCustomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickedListener");
        }
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.video, parent, false);
        final VideoViewHolder holder = new VideoViewHolder(inflatedView);
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(CONSTANTS.BASE_YOUTUBE_VIDEO_URL+holder.mKey, "video");
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        String key = getItem(position);
        if(key == null) return;

        // TODO: Add Check if key is for youtube video
        holder.mProgressBar.setVisibility(View.VISIBLE);
        Log.i(String.valueOf(position), String.format(CONSTANTS.YOUTUBE_THUMBNAIL_URL, key));
        Picasso.with(mContext).load(String.format(CONSTANTS.YOUTUBE_THUMBNAIL_URL, key)).resize(0, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mProgressBar.setVisibility(View.INVISIBLE);
        holder.mKey = key;
    }

    @Override
    public int getItemCount() {
        return (mVideoKeys == null ? 0 : mVideoKeys.size());
    }

    // returns key for video at position in mVideoKeys array-list
    public String getItem(int position) {
        return (mVideoKeys == null ? null : mVideoKeys.get(position));
    }

    public interface onClickCustomListener {
        void onItemClick(String url, String type);
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        View mItemView;
        View mProgressBar;
        String mKey;

        public VideoViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.video_thumbnail);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
