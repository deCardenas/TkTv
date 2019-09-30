package com.aprendiendo.tktv.presenter.detail;

import com.aprendiendo.tktv.interfaces.Detail;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class DetailPresenter<T> implements Detail.Presenter, Detail.Listener<T> {
    Detail.View<T> view;
    Detail.Model<T> model;

    public DetailPresenter(Detail.View view) {
        this.view = view;
    }

    @Override
    public void onLoadDetailDb(String id) {
        view.showProgress();
        model.loadDetailDb(this, id);
    }

    @Override
    public void onLoadDetail(String id) {
        view.showProgress();
        model.loadDetail(this, id);
    }

    @Override
    public void onFinished(T item) {
        view.loadItem(item);
        view.hideProgress();
    }

    @Override
    public void onError(String error) {
        view.setError(error);
    }
}
