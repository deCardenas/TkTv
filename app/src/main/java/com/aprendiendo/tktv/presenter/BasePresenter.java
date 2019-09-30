package com.aprendiendo.tktv.presenter;

import android.util.Log;

import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Base;
import com.aprendiendo.tktv.model.BaseModel;
import com.aprendiendo.tktv.model.MovieModel;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 08/03/2018.
 */

public abstract class BasePresenter<T> implements Base.Presenter, Base.Listener<T> {
    final static String TAG = BasePresenter.class.getSimpleName();
    Base.View<T> view;
    Base.Model<T> model;

    public BasePresenter(Base.View<T> view) {
        this.view = view;
    }

    @Override
    public void onLoadPopularDb() {
        Log.i(TAG, "onLoadPopularDb");
        view.showProgress();
        model.onLoadPopularDb(this);
    }

    @Override
    public void onLoadPopularFirst() {
        view.showProgress();
        model.loadPopularFirstPage(this);
    }

    @Override
    public void onLoadPopularNext() {
        model.loadPopularNextPage(this);
    }

    @Override
    public void onFinishedDb(ArrayList<T> items) {
        view.loadDb(items);
        view.hideProgress();
    }

    @Override
    public void onFinishedFirst(ArrayList<T> items, boolean isLastPage, int last) {
        view.loadFirst(items, isLastPage, last);
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
