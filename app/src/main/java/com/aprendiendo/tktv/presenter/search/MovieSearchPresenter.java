package com.aprendiendo.tktv.presenter.search;

import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Search;
import com.aprendiendo.tktv.model.search.MovieSearchModel;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MovieSearchPresenter extends SearchPresenter<Movie> {
    public MovieSearchPresenter(Search.View<Movie> view) {
        super(view);
        model = new MovieSearchModel();
    }
}
