package com.example.kapillamba4.theopensourcemovieapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private MenuItem mSearchMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        handleIntent(getIntent());
    }

    // single top mode
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String query = intent.getStringExtra(SearchManager.QUERY);
        Log.i("Query : ", query);
        //use the query to search your data somehow
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
        intent.putExtra("query", s);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

}
