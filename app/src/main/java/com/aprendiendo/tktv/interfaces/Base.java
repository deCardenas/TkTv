package com.aprendiendo.tktv.interfaces;

import com.aprendiendo.tktv.data.Movie;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public interface Base {
    interface View<T>{
        void showProgress();
        void hideProgress();
        void setError(String error);
        void loadDb(ArrayList<T> items);
        void loadFirst(ArrayList<T> items, boolean isLastPage, int totalPages);
        void loadNext(ArrayList<T> items, boolean isLastPage);
        void showMessage(String message);
    }
    interface Presenter{
        void onLoadPopularDb();
        void onLoadPopularFirst();
        void onLoadPopularNext();
    }
    interface Model<T>{
        void onLoadPopularDb(Listener<T> listener);
        void loadPopularFirstPage(Listener<T> listener);
        void loadPopularNextPage(Listener<T> listener);
    }
    interface Listener<T>{
        void onFinishedDb(ArrayList<T> items);
        void onFinishedFirst(ArrayList<T> items, boolean isLastPage, int totalPages);
        void onFinishedNext(ArrayList<T> items, boolean isLastPage);
        void onError(String error);
        void onMessage(String message);
    }
    interface AdapterListener {
        void onClick(String id);
    }
}
