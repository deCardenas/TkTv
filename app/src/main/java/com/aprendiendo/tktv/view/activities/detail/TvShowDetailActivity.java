package com.aprendiendo.tktv.view.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiendo.tktv.R;
import com.aprendiendo.tktv.data.TvShow;
import com.aprendiendo.tktv.interfaces.Detail;
import com.aprendiendo.tktv.presenter.detail.TvShowDetailPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class TvShowDetailActivity extends DetailActivity<TvShow> {
    private ImageView backdrop_path;
    TextView txt_name, txt_runtime, txt_original_name, txt_seasons, txt_episodes;
    TextView txt_overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);
        setTitle("");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        backdrop_path = (ImageView) findViewById(R.id.backdrop_path);
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_runtime = (TextView) findViewById(R.id.txt_runtime);
        txt_original_name = (TextView) findViewById(R.id.txt_original_name);
        txt_seasons = (TextView) findViewById(R.id.txt_seasons);
        txt_episodes = (TextView) findViewById(R.id.txt_episodes);
        txt_overview = (TextView) findViewById(R.id.txt_overview);
        txt_homepage = (TextView) findViewById(R.id.txt_homepage);
        progress = (ProgressBar) findViewById(R.id.progress);
        layout = (CoordinatorLayout) findViewById(R.id.content);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new TvShowDetailPresenter(this);
        Intent i = getIntent();
        if (i != null) {
            if (isConnected())
                presenter.onLoadDetail(i.getStringExtra("ID"));
            else presenter.onLoadDetailDb(i.getStringExtra("ID"));
        }
    }

    @Override
    public void loadItem(TvShow item) {
        Glide.with(this).load(item.getBackdrop_path()).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                centerCrop().crossFade().into(backdrop_path);
        txt_name.setText(item.getName());
        txt_runtime.setText(item.getRunTimeFormat());
        txt_original_name.setText(item.getOriginal_name());
        txt_seasons.setText(String.valueOf(item.getNumber_of_seasons()));
        txt_episodes.setText(String.valueOf(item.getNumber_of_episodes()));
        txt_overview.setText(item.getOverview());
        txt_homepage.setText(item.getHomepage());
    }

}
