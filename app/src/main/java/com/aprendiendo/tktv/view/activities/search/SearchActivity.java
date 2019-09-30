package com.aprendiendo.tktv.view.activities.search;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.interfaces.Base;
import com.aprendiendo.tktv.interfaces.Search;
import com.aprendiendo.tktv.util.PaginationScrollListener;
import com.aprendiendo.tktv.view.adapter.BaseAdapter;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public abstract class SearchActivity<T> extends AppCompatActivity implements Search.View<T>,
        Base.AdapterListener {
    static final String TAG = "Search";
    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView txt_hint;
    BaseAdapter<T> adapter;
    LinearLayoutManager layoutManager;
    Search.Presenter presenter;

    static final int PAGE_START = 1;
    int current = PAGE_START;
    int lastPage = 2;
    boolean isLoading = false, isLastPage = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        txt_hint = (TextView) findViewById(R.id.txt_hint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setUpRecyclerView() {
        if (getLoaderManager() != null)
            recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public LinearLayoutManager getLayoutManager() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        return layoutManager;
    }

    public PaginationScrollListener getPaginationScroll(LinearLayoutManager layoutManager) {
        return new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                current += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.onLoadSearchedNext();
                        isLoading = false;
                    }
                }, 5000);
            }

            @Override
            public int getTotalPageCount() {
                return lastPage;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        Log.e(TAG, "ingrese al menu!");
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setInputType(InputType.TYPE_CLASS_TEXT);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("SearchView", "estoy aqui aun");
                try {
                    adapter.clear();
                    presenter.onLoadSearched(query);
                    searchView.setQuery(query, false);
                    searchView.clearFocus();
                } catch (Exception e) {
                    Log.e("SearchView", e.getLocalizedMessage() + " OTTO " + e.getMessage());
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        return true;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        txt_hint.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setError(String error) {
        Log.e(TAG, error);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
    }

}
