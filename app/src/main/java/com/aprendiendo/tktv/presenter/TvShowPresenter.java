package com.aprendiendo.tktv.presenter;

import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.interfaces.Base;
import com.aprendiendo.tktv.model.TvShowModel;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowPresenter extends BasePresenter<TvShow> {
    public TvShowPresenter(Base.View<TvShow> view) {
        super(view);
        model = new TvShowModel();
    }
}
