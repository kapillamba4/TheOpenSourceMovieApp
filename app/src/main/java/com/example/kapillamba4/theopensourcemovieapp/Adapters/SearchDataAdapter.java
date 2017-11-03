package com.example.kapillamba4.theopensourcemovieapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapillamba4.theopensourcemovieapp.Entities.SearchItem;
import com.example.kapillamba4.theopensourcemovieapp.R;

import java.util.ArrayList;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Created by kapil on 1/11/17.
 */

public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.SearchViewHolder> implements Filterable {
    private ArrayList<SearchItem> mSearchItems;
    private Context mContext;

    @Override
    public SearchDataAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchDataAdapter.SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }

    @Override
    public android.widget.Filter getFilter() {
        return null;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mRelease;
        View mItemView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
         //   mImageView = itemView.findViewById(R.id.search_card_thumbnail);
         //   mTitle = itemView.findViewById(R.id.search_card_title);
         //   mRelease = itemView.findViewById(R.id.search_card_release);
        }
    }

}