package com.aprendiendo.tktv.view.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.presenter.search.MovieSearchPresenter;
import com.aprendiendo.tktv.presenter.search.TvShowSearchPresenter;
import com.aprendiendo.tktv.view.activities.detail.MovieDetailActivity;
import com.aprendiendo.tktv.view.activities.detail.TvShowDetailActivity;
import com.aprendiendo.tktv.view.adapter.MovieAdapter;
import com.aprendiendo.tktv.view.adapter.TvShowAdapter;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class SearchTvShowActivity extends SearchActivity<TvShow>{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txt_hint.setText(R.string.search_tv_shows);
        adapter = new TvShowAdapter(this);
        adapter.setListener(this);
        presenter = new TvShowSearchPresenter(this);
        setUpRecyclerView();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(getPaginationScroll(layoutManager));
    }

    @Override
    public void loadFirst(ArrayList<TvShow> items, boolean isLastPage, int totalPages) {
        this.isLastPage = isLastPage;
        this.lastPage = totalPages;
        Log.d(TAG, "loadFirstPage: " + current);
        adapter.addAll(items);
        if (!isLastPage) adapter.addLoadingFooter();
    }

    @Override
    public void loadNext(ArrayList<TvShow> item, boolean isLastPage) {
        this.isLastPage = isLastPage;
        Log.d(TAG, "loadNextPage: " + current);
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(item);
        if (!isLastPage) adapter.addLoadingFooter();
    }

    @Override
    public void onClick(String id) {
        Intent i = new Intent(this, TvShowDetailActivity.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
}
