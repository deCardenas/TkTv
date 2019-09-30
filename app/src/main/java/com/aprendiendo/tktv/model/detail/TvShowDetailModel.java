package com.aprendiendo.tktv.model.detail;

import android.util.Log;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.db.contract.TvShowContract;
import com.aprendiendo.tktv.interfaces.Detail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowDetailModel implements Detail.Model<TvShow> {
    @Override
    public void loadDetailDb(Detail.Listener<TvShow> listener, String id) {
        TvShow movie = TvShowContract.getTvShowById(Integer.parseInt(id));
        if (movie != null)
            listener.onFinished(movie);
        else
            listener.onError("Not found");
    }

    @Override
    public void loadDetail(final Detail.Listener<TvShow> listener, String id) {
        Service.TvShows service = Client.getClient().create(Service.TvShows.class);
        Call<TvShow> call = service.getTvShowDetail(id, BuildConfig.THE_MOVIE_DB_API_TOKEN, "en-US");
        call.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                switch (response.code()) {
                    case 200:
                        long id = TvShowContract.update(response.body());
                        Log.d("DetailModel", "id:" + id);
                        listener.onFinished(response.body());
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
            public void onFailure(Call<TvShow> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });

    }
}
