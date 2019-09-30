package com.aprendiendo.tktv.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.interfaces.Base;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by Ana Diaz on 07/03/2018.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    Base.AdapterListener listener;
    private ArrayList<T> items;
    public Context context;

    private boolean isLoadingAdded = false;

    public BaseAdapter(Context context, Base.AdapterListener listener) {
        this.context = context;
        this.listener = listener;
        items = new ArrayList<>();
    }

    public BaseAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public void setListener(Base.AdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item = items.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                setViewHolder(item, (BaseViewHolder) holder);
                break;
        }
    }

    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new BaseViewHolder(v1);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == items.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    //ABSTRACT METHOD
    public abstract void setViewHolder(T item, BaseViewHolder viewHolder);

    public RequestListener<String, GlideDrawable> glideDrawableRequestListener(final BaseViewHolder holder){
        return new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                holder.progress.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.progress.setVisibility(View.GONE);
                return false;
            }
        };
    }

    //HELPERS

    public void add(T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addAll(ArrayList<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    public void remove(T item) {
        int position = items.indexOf(item);
        if (position >= 0) {
            items.remove(item);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0)
            remove(getItem(0));
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(null);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = items.size() - 1;
        items.remove(position);
        notifyItemRemoved(position);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    //get and set

    public T getItem(int position) {
        return items.get(position);
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    //ViewHolders
    class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView path;
        TextView id;
        TextView txt_title;
        TextView txt_description;
        ProgressBar progress;

        public BaseViewHolder(View itemView) {
            super(itemView);
            path = (ImageView) itemView.findViewById(R.id.path);
            id = (TextView) itemView.findViewById(R.id.card_id);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_description = (TextView) itemView.findViewById(R.id.txt_description);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(id.getText().toString());
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
