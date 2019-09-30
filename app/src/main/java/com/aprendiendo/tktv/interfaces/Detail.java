package com.aprendiendo.tktv.interfaces;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public interface Detail {
    interface View<T>{
        void showProgress();
        void hideProgress();
        void loadItem(T item);
        void setError(String error);
    }
    interface Presenter{
        void onLoadDetailDb(String id);
        void onLoadDetail(String id);
    }
    interface Model<T>{
        void loadDetailDb(Listener<T> listener, String id);
        void loadDetail(Listener<T> listener, String id);
    }
    interface Listener<T>{
        void onFinished(T item);
        void onError(String error);
    }
}
