package com.kapillamba4.thecompletemovieguide.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.kapillamba4.thecompletemovieguide.Adapters.SearchDataAdapter;
import com.kapillamba4.thecompletemovieguide.Entities.MultiSearch;
import com.kapillamba4.thecompletemovieguide.Entities.SearchItem;
import com.kapillamba4.thecompletemovieguide.R;
import com.kapillamba4.thecompletemovieguide.Services.CommonService;
import com.kapillamba4.thecompletemovieguide.Utils.CONSTANTS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchDataAdapter.onClickCustomListener {

    private MenuItem mSearchMenu;
    private Retrofit mBuilder;
    private RecyclerView mRecyclerView;
    private ArrayList<SearchItem> multiSearches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRecyclerView = findViewById(R.id.search_results);
        handleIntent(getIntent());

    }

    // single top mode
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        CommonService commonService = mBuilder.create(CommonService.class);
        Call<MultiSearch> multiSearchCall = commonService.getSearchResult(CONSTANTS.MOVIE_DB_API_KEY, intent.getStringExtra(SearchManager.QUERY), "1");
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        verticalLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(verticalLayoutManager);
        multiSearchCall.enqueue(new Callback<MultiSearch>() {
            @Override
            public void onResponse(Call<MultiSearch> call, Response<MultiSearch> response) {
                multiSearches = new ArrayList<>(response.body().getResults());
                SearchDataAdapter searchDataAdapter = new SearchDataAdapter(SearchActivity.this, multiSearches);
                mRecyclerView.setAdapter(searchDataAdapter);
                searchDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MultiSearch> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mSearchMenu = menu.findItem(R.id.search);
        ((SearchView) mSearchMenu.getActionView()).setOnQueryTextListener(SearchActivity.this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        mSearchMenu.collapseActionView();
        Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
        intent.putExtra(SearchManager.QUERY, s);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onItemClick(String id, String type) {
        Intent intent;
        switch (type) {
            case "tv":
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case "movie":
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                startActivity(intent);
                break;
        }
    }
}
