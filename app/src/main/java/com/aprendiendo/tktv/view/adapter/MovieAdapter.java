package com.aprendiendo.tktv.view.adapter;

import android.content.Context;
import android.util.Log;

import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Base;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Ana Diaz on 08/03/2018.
 */

public class MovieAdapter extends BaseAdapter<Movie> {
    public MovieAdapter(Context context, Base.AdapterListener listener) {
        super(context, listener);
    }

    public MovieAdapter(Context context) {
        super(context);
    }

    @Override
    public void setViewHolder(Movie item, BaseViewHolder viewHolder) {
        Log.i("MovieAdapter","Titulo: " + item.getTitle()+ ".  Descripcion: " + item.getOverview());
        viewHolder.id.setText(String.valueOf(item.getId()));
        viewHolder.txt_title.setText(item.getTitle());
        viewHolder.txt_description.setText(item.getOverview());
        Glide.with(context).load(item.getPoster_path()).listener(glideDrawableRequestListener(viewHolder)).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                centerCrop().crossFade().into(viewHolder.path);
    }
}
