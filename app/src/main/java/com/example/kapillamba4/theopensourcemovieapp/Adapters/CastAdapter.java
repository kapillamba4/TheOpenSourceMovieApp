package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Entities.Cast;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kapil on 10/11/17.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private ArrayList<Cast> mCast;
    private Context mContext;

    public CastAdapter(Context context, ArrayList<Cast> casts) {
        mCast = casts;
        mContext = context;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.cast, parent, false);
        return new CastViewHolder(inflatedView);
    }

    Integer errorHandlerForCastImage(CastViewHolder holder) {
        Picasso.with(mContext).load(R.raw.default_profile_pic).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        return null;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        holder.mProgressBar.setVisibility(View.VISIBLE);
        holder.mNameTextView.setText(mCast.get(position).getName());
        holder.mCharacterTextView.setText(mCast.get(position).getCharacter());
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                errorHandlerForCastImage(holder);
            }
        });

        builder.build().load(CONSTANTS.BASE_POSTER_URL_SMALL + mCast.get(position).getProfilePath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mCast.size();
    }

    static class CastViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mNameTextView;
        TextView mCharacterTextView;
        View mItemView;
        View mProgressBar;

        public CastViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.cast_image);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
            mNameTextView = itemView.findViewById(R.id.cast_name);
            mCharacterTextView = itemView.findViewById(R.id.cast_character);
        }
    }
}
