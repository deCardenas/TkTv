package com.aprendiendo.tktv.model.detail;

import android.util.Log;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.db.contract.MovieContract;
import com.aprendiendo.tktv.interfaces.Detail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MovieDetailModel implements Detail.Model<Movie> {
    @Override
    public void loadDetailDb(Detail.Listener<Movie> listener, String id) {
        Movie movie = MovieContract.getMovieById(Integer.parseInt(id));
        if (movie != null)
            listener.onFinished(movie);
        else
            listener.onError("Not found");
    }

    @Override
    public void loadDetail(final Detail.Listener<Movie> listener, String id) {
        Service.Movies service = Client.getClient().create(Service.Movies.class);
        Call<Movie> call = service.getMovieDetail(id, BuildConfig.THE_MOVIE_DB_API_TOKEN, "en-US");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                switch (response.code()) {
                    case 200:
                        long id = MovieContract.update(response.body());
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
            public void onFailure(Call<Movie> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });

    }
}
