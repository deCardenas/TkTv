package com.aprendiendo.tktv.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.aprendiendo.tktv.data.TvShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Ana Diaz on 09/03/2018.
 */

public class TvShowAdapter extends BaseAdapter<TvShow> {

    public TvShowAdapter(Context context) {
        super(context);
    }

    @Override
    public void setViewHolder(TvShow item, BaseViewHolder viewHolder) {
        viewHolder.id.setText(String.valueOf(item.getId()));
        viewHolder.txt_title.setText(item.getName());
        viewHolder.txt_description.setText(item.getOverview());
        if (isConnected())
        Glide.with(context).load(item.getPoster_path()).listener(glideDrawableRequestListener(viewHolder)).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                centerCrop().crossFade().into(viewHolder.path);
        else{
            Bitmap b = BitmapFactory.decodeByteArray(item.getPoster_byte(), 1, item.getPoster_byte().length);
            viewHolder.path.setImageBitmap(b);
        }
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
