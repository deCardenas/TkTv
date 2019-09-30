package com.aprendiendo.tktv.model.search;

import com.aprendiendo.tktv.interfaces.Search;
import com.aprendiendo.tktv.model.BaseModel;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public abstract class SearchModel<T> implements Search.Model<T> {
    String TAG = BaseModel.class.getSimpleName();
    int current = 0;
    int last = 0;
    String query = "";
}
