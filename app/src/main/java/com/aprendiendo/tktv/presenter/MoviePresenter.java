package com.aprendiendo.tktv.presenter;

import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Base;
import com.aprendiendo.tktv.model.MovieModel;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MoviePresenter extends BasePresenter<Movie> {
    public MoviePresenter(Base.View<Movie> view) {
        super(view);
        model = new MovieModel();
    }
}
