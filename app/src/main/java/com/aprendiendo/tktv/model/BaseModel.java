package com.aprendiendo.tktv.model;

import android.util.Log;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 08/03/2018.
 */

public abstract class BaseModel<T> implements Base.Model<T> {
    String TAG = BaseModel.class.getSimpleName();
    static final int PAGE_START = 1;
    int current = PAGE_START;
    int last = 0;

}
