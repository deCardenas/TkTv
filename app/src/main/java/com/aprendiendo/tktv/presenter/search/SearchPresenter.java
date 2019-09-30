package com.aprendiendo.tktv.presenter.search;

import com.aprendiendo.tktv.interfaces.Search;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public abstract class SearchPresenter<T> implements Search.Presenter<T>, Search.Listener<T> {
    private Search.View<T> view;
    Search.Model<T> model;

    SearchPresenter(Search.View<T> view) {
        this.view = view;
    }

    @Override
    public void onLoadSearched(String quey) {
        view.showProgress();
        model.onLoadSearched(this, quey);
    }

    @Override
    public void onLoadSearchedNext() {
        model.onLoadSearchedNext(this);
    }

    @Override
    public void onFinishedFirst(ArrayList<T> items, boolean isLastPage, int totalPages) {
        view.loadFirst(items, isLastPage, totalPages);
        view.hideProgress();
    }

    @Override
    public void onFinishedNext(ArrayList<T> items, boolean isLastPage) {
        view.loadNext(items, isLastPage);
    }

    @Override
    public void onError(String error) {
        view.setError(error);
    }

    @Override
    public void onMessage(String message) {
        view.showMessage(message);
    }
}
