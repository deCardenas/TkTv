package com.aprendiendo.tktv.view.adapter;

import android.content.Context;

import com.aprendiendo.tktv.data.Person;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class PersonAdapter extends BaseAdapter<Person> {
    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    public void setViewHolder(Person item, BaseViewHolder viewHolder) {
        viewHolder.id.setText(String .valueOf(item.getId()));
        viewHolder.txt_title.setText(item.getName());
        viewHolder.txt_description.setText(item.getKnownFor());
        Glide.with(context).load(item.getProfile_path()).listener(glideDrawableRequestListener(viewHolder)).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                centerCrop().crossFade().into(viewHolder.path);
    }
}
