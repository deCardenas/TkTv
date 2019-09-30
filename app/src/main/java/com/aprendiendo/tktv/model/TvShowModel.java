package com.aprendiendo.tktv.model;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.db.contract.ListContract;
import com.aprendiendo.tktv.db.contract.TvShowContract;
import com.aprendiendo.tktv.interfaces.Base;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowModel extends BaseModel<TvShow> {
    @Override
    public void onLoadPopularDb(Base.Listener<TvShow> listener) {
        ArrayList<TvShow> tvShows = TvShowContract.getAllTvShows();
        if (tvShows != null && tvShows.size() != 0) {
            listener.onFinishedDb(tvShows);
        } else {
            listener.onMessage("THERE IS NO DATA SAVED IN THE DB");
        }
    }

    @Override
    public void loadPopularFirstPage(final Base.Listener<TvShow> listener) {
        Service.TvShows service = Client.getClient().create(Service.TvShows.class);
        Call<List<TvShow>> call = service.getPopularTvShows(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", 1);
        call.enqueue(new Callback<List<TvShow>>() {
            @Override
            public void onResponse(Call<List<TvShow>> call, Response<List<TvShow>> response) {
                switch (response.code()) {
                    case 200:
                        List list = ListContract.getListByType(ListContract.TV_SHOW);
                        List<TvShow> tvShows = response.body();
                        tvShows.setType(ListContract.TV_SHOW);
                        last = tvShows.getTotal_pages();
                        if (list != null) {
                            if (list.getTotal_results() != tvShows.getTotal_results()) {
                                TvShowContract.delete();
                                for (TvShow tvShow : tvShows.getResults()) {
                                    long id = TvShowContract.insert(tvShow);
                                    Log.i(TAG, "id: " + id + " Title: " + tvShow.getName());
                                }
                                ListContract.update(tvShows);
                            }
                        } else {
                            ListContract.insert(tvShows);
                            for (TvShow tvShow : tvShows.getResults()) {
                                long id = TvShowContract.insert(tvShow);
                                Log.i(TAG, "id: " + id + " Title: " + tvShow.getName());
                            }
                        }
                        listener.onFinishedFirst(tvShows.getResults(), (current == last), last);
                        break;
                    case 401:
                        listener.onError("Invalid API key: You must be granted a valid key");
                        break;
                    case 404:
                        listener.onError("The resource you requested could not be found");
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<TvShow>> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loadPopularNextPage(final Base.Listener<TvShow> listener) {
        current++;
        Service.TvShows service = Client.getClient().create(Service.TvShows.class);
        Call<List<TvShow>> call = service.getPopularTvShows(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", current);
        call.enqueue(new Callback<List<TvShow>>() {
            @Override
            public void onResponse(Call<List<TvShow>> call, Response<List<TvShow>> response) {
                switch (response.code()) {
                    case 200:
                        List<TvShow> tvShows = response.body();
                        tvShows.getResults().get(0).setPoster_byte(toBytes(tvShows.getResults().get(0).getPoster_path()));
                        listener.onFinishedNext(tvShows.getResults(), (current == last));
                        break;
                    case 401:
                        listener.onError("Invalid API key: You must be granted a valid key");
                        break;
                    case 404:
                        listener.onError("The resource you requested could not be found");
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<TvShow>> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });
    }

    private byte[] toBytes(String mUrl){
        Bitmap bmp = getBitmapFromURL(mUrl);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        assert bmp != null;
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            return null;
        }
    }
}
