package com.aprendiendo.tktv.model;

import android.util.Log;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.db.contract.ListContract;
import com.aprendiendo.tktv.db.contract.MovieContract;
import com.aprendiendo.tktv.interfaces.Base;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MovieModel extends BaseModel<Movie> {
    @Override
    public void onLoadPopularDb(Base.Listener<Movie> listener) {
        ArrayList<Movie> movies = MovieContract.getAllMovies();
        if (movies != null && movies.size() != 0) {
            listener.onFinishedDb(movies);
        } else {
            listener.onMessage("THERE IS NO DATA SAVED IN THE DB");
        }
    }

    @Override
    public void loadPopularFirstPage(final Base.Listener<Movie> listener) {
        Service.Movies service = Client.getClient().create(Service.Movies.class);
        Call<List<Movie>> call = service.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", 1);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                switch (response.code()) {
                    case 200:
                        List list = ListContract.getListByType(ListContract.MOVIE);
                        List<Movie> movies = response.body();
                        last = movies.getTotal_pages();
                        if (list != null) {
                            if (list.getTotal_results() != movies.getTotal_results()) {
                                MovieContract.delete();
                                for (Movie movie : movies.getResults()) {
                                    long id = MovieContract.insert(movie);
                                    Log.i(TAG, "id: " + id + " Title: " + movie.getTitle());
                                }
                                ListContract.update(movies);
                            }
                        } else {
                            ListContract.insert(movies);
                            for (Movie movie : movies.getResults()) {
                                long id = MovieContract.insert(movie);
                                Log.i(TAG, "id: " + id + " Title: " + movie.getTitle());
                            }
                        }
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
    public void loadPopularNextPage(final Base.Listener<Movie> listener) {
        current++;
        Service.Movies service = Client.getClient().create(Service.Movies.class);
        Call<List<Movie>> call = service.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", current);
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
