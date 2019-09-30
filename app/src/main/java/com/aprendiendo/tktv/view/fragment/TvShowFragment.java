package com.aprendiendo.tktv.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.presenter.TvShowPresenter;
import com.aprendiendo.tktv.util.PaginationScrollListener;
import com.aprendiendo.tktv.view.activities.detail.MovieDetailActivity;
import com.aprendiendo.tktv.view.activities.detail.TvShowDetailActivity;
import com.aprendiendo.tktv.view.activities.search.SearchMovieActivity;
import com.aprendiendo.tktv.view.activities.search.SearchTvShowActivity;
import com.aprendiendo.tktv.view.adapter.TvShowAdapter;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowFragment extends BaseFragment<TvShow> {
    @SuppressLint("StaticFieldLeak")
    private static TvShowFragment instance;

    public static TvShowFragment newInstance(){
        if (instance == null)
            instance = new TvShowFragment();
        return instance;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("");
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        adapter = new TvShowAdapter(getContext());
        adapter.setListener(this);
        presenter = new TvShowPresenter(this);
        setUpRecyclerView();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                current += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.onLoadPopularNext();
                        isLoading = false;
                    }
                }, 5000);
            }

            @Override
            public int getTotalPageCount() {
                return 20;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        if (isConnected())
            presenter.onLoadPopularFirst();
        else
            presenter.onLoadPopularDb();
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent i = new Intent(getActivity(), SearchTvShowActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadDb(ArrayList<TvShow> items) {
        Log.d(TAG, "Load from db");
        adapter.addAll(items);
    }

    @Override
    public void loadFirst(ArrayList<TvShow> movies, boolean isLastPage, int lastPage) {
        this.isLastPage = isLastPage;
        this.lastPage = lastPage;
        Log.d(TAG, "loadFirstPage: " + current);
        adapter.addAll(movies);
        if (!isLastPage) adapter.addLoadingFooter();
    }

    @Override
    public void loadNext(ArrayList<TvShow> movies, boolean isLastPage) {
        this.isLastPage = isLastPage;
        Log.d(TAG, "loadNextPage: " + current);
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(movies);
        if (!isLastPage) adapter.addLoadingFooter();
    }

    @Override
    public void onClick(String id) {
        Intent i = new Intent(getActivity(), TvShowDetailActivity.class);
        i.putExtra("ID", id);
        startActivity(i);
    }
}
