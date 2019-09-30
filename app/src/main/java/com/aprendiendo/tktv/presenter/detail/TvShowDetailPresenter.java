package com.aprendiendo.tktv.presenter.detail;

import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.interfaces.Detail;
import com.aprendiendo.tktv.model.detail.TvShowDetailModel;

/**
 * Created by Ana Diaz on 10/03/2018.
 */

public class TvShowDetailPresenter extends DetailPresenter<TvShow> {
    public TvShowDetailPresenter(Detail.View view) {
        super(view);
        model = new TvShowDetailModel();
    }
}
