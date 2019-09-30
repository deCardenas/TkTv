package com.aprendiendo.tktv.interfaces;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public interface Search {
    interface View<T> {
        void showProgress();

        void hideProgress();

        void setError(String error);

        void loadFirst(ArrayList<T> items, boolean isLastPage, int totalPages);

        void loadNext(ArrayList<T> item, boolean isLastPage);

        void showMessage(String message);

    }

    interface Presenter<T> {
        void onLoadSearched(String quey);

        void onLoadSearchedNext();
    }

    interface Model<T> {
        void onLoadSearched(Listener<T> listener, String query);

        void onLoadSearchedNext(Listener<T> listener);
    }

    interface Listener<T> {
        void onFinishedFirst(ArrayList<T> items, boolean isLastPage, int totalPages);

        void onFinishedNext(ArrayList<T> items, boolean isLastPage);

        void onError(String error);

        void onMessage(String message);
    }
}
