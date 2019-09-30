package com.aprendiendo.tktv.model.search;

import android.util.Log;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Search;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MovieSearchModel extends SearchModel<Movie> {

    @Override
    public void onLoadSearched(final Search.Listener<Movie> listener, String query) {
        current = 1;
        this.query = query;
        Service.Movies service = Client.getClient().create(Service.Movies.class);
        Call<List<Movie>> call = service.getSearchedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", current, query);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                switch (response.code()) {
                    case 200:
                        List<Movie> movies = response.body();
                        last = movies.getTotal_pages();
                        listener.onFinishedFirst(movies.getResults(), (current == last), last);
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
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onLoadSearchedNext(final Search.Listener<Movie> listener) {
        current++;
        Service.Movies service = Client.getClient().create(Service.Movies.class);
        Call<List<Movie>> call = service.getSearchedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", current, query);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                switch (response.code()) {
                    case 200:
                        List<Movie> movies = response.body();
                        listener.onFinishedNext(movies.getResults(), (current == last));
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
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });
    }
}
