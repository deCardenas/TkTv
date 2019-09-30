package com.aprendiendo.tktv.presenter.detail;

import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Detail;
import com.aprendiendo.tktv.model.detail.MovieDetailModel;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class MovieDetailPresenter extends DetailPresenter<Movie> {
    public MovieDetailPresenter(Detail.View view) {
        super(view);
        model = new MovieDetailModel();
    }
}
