package com.aprendiendo.tktv.view.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.interfaces.Base;
import com.aprendiendo.tktv.util.PaginationScrollListener;
import com.aprendiendo.tktv.view.adapter.BaseAdapter;

import java.util.ArrayList;


/**
 * Created by Ana Diaz on 09/03/2018.
 */

public abstract class BaseFragment<T> extends Fragment implements Base.View<T>,Base.AdapterListener {
    static final String TAG = "Fragment";
    ProgressBar progressBar;
    RecyclerView recyclerView;
    BaseAdapter<T> adapter;
    LinearLayoutManager layoutManager;

    static final int PAGE_START = 1;
    int current = PAGE_START;
    int lastPage = 2;
    boolean isLoading = false, isLastPage = false;

    Base.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("");
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        adapter = createAdapter();
        presenter = createPresenter();
        setUpRecyclerView();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(getPaginationScroll(layoutManager));
        presenter.onLoadPopularFirst();
        setHasOptionsMenu(true);


    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.nav, menu);
    }

    void setUpRecyclerView() {
        if (getLoaderManager() != null)
            recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public LinearLayoutManager getLayoutManager() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        return layoutManager;
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
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
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
    }
}
