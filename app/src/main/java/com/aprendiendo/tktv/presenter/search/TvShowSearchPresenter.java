package com.aprendiendo.tktv.presenter.search;

import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.interfaces.Search;
import com.aprendiendo.tktv.model.search.TvShowSearchModel;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowSearchPresenter extends SearchPresenter<TvShow> {
    public TvShowSearchPresenter(Search.View<TvShow> view) {
        super(view);
        model = new TvShowSearchModel();
    }
}
