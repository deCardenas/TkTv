package com.aprendiendo.tktv.view.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.presenter.search.MovieSearchPresenter;
import com.aprendiendo.tktv.view.activities.detail.MovieDetailActivity;
import com.aprendiendo.tktv.view.adapter.MovieAdapter;

import java.util.ArrayList;

public class SearchMovieActivity extends SearchActivity<Movie> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txt_hint.setText(R.string.search_movies);
        adapter = new MovieAdapter(this);
        adapter.setListener(this);
        presenter = new MovieSearchPresenter(this);
        setUpRecyclerView();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(getPaginationScroll(layoutManager));
    }

    @Override
    public void loadFirst(ArrayList<Movie> items, boolean isLastPage, int totalPages) {
        this.isLastPage = isLastPage;
        this.lastPage = totalPages;
        Log.d(TAG, "loadFirstPage: " + current);
        adapter.addAll(items);
        if (!isLastPage) adapter.addLoadingFooter();
    }

    @Override
    public void loadNext(ArrayList<Movie> item, boolean isLastPage) {
        this.isLastPage = isLastPage;
        Log.d(TAG, "loadNextPage: " + current);
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(item);
        if (!isLastPage) adapter.addLoadingFooter();
    }

    @Override
    public void onClick(String id) {
        Intent i = new Intent(this, MovieDetailActivity.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
}
