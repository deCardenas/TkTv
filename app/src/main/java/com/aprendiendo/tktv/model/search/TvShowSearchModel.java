package com.aprendiendo.tktv.model.search;

import com.aprendiendo.tktv.BuildConfig;
import com.aprendiendo.tktv.api.Client;
import com.aprendiendo.tktv.api.Service;
import com.aprendiendo.tktv.data.List;
import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.interfaces.Search;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowSearchModel extends SearchModel<TvShow> {
    @Override
    public void onLoadSearched(final Search.Listener<TvShow> listener, String query) {
        current = 1;
        this.query = query;
        Service.TvShows service = Client.getClient().create(Service.TvShows.class);
        Call<List<TvShow>> call = service.getSearchedTvShows(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", current, query);
        call.enqueue(new Callback<List<TvShow>>() {
            @Override
            public void onResponse(Call<List<TvShow>> call, Response<List<TvShow>> response) {
                switch (response.code()) {
                    case 200:
                        List<TvShow> tvShowList = response.body();
                        last = tvShowList.getTotal_pages();
                        listener.onFinishedFirst(tvShowList.getResults(), (current == last), last);
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
    public void onLoadSearchedNext(final Search.Listener<TvShow> listener) {
        current++;
        Service.TvShows service = Client.getClient().create(Service.TvShows.class);
        Call<List<TvShow>> call = service.getSearchedTvShows(BuildConfig.THE_MOVIE_DB_API_TOKEN, "es-ES", current, query);
        call.enqueue(new Callback<List<TvShow>>() {
            @Override
            public void onResponse(Call<List<TvShow>> call, Response<List<TvShow>> response) {
                switch (response.code()) {
                    case 200:
                        List<TvShow> tvShowList = response.body();
                        listener.onFinishedNext(tvShowList.getResults(), (current == last));
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
}
