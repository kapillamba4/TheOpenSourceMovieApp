package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Entities.SearchItem;
import com.example.kapillamba4.theopensourcemovieapp.R;
import com.example.kapillamba4.theopensourcemovieapp.Utils.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kapil on 1/11/17.
 */

public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.SearchViewHolder> {
    private ArrayList<SearchItem> mSearchItems;
    private Context mContext;

    public SearchDataAdapter(Context context, ArrayList<SearchItem> searchItems) {
        mSearchItems = searchItems;
        mContext = context;
    }

    @Override
    public SearchDataAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.search_item_card_layout, parent, false);
        return new SearchViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(SearchDataAdapter.SearchViewHolder holder, int position) {
        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL+mSearchItems.get(position).getPosterPath()).into(holder.mImageView);
        holder.mTitle.setText(mSearchItems.get(position).getTitle());
        holder.mRelease.setText(mSearchItems.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mRelease;
        View mItemView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.search_card_thumbnail);
            mTitle = itemView.findViewById(R.id.search_card_title);
            mRelease = itemView.findViewById(R.id.search_card_release);
        }
    }

}