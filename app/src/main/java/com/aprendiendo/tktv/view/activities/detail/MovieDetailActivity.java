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
import com.aprendiendo.tktv.data.Movie;
import com.aprendiendo.tktv.interfaces.Detail;
import com.aprendiendo.tktv.presenter.detail.MovieDetailPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.NumberFormat;
import java.util.Locale;

public class MovieDetailActivity extends DetailActivity<Movie> {
    private ImageView backdrop_path;
    TextView txt_title, txt_status, txt_release, txt_adult, txt_runtime, txt_budget;
    TextView txt_revenue, txt_overview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setTitle("");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        backdrop_path = (ImageView) findViewById(R.id.backdrop_path);
        txt_title = (TextView) findViewById(R.id.txt_name);
        txt_status = (TextView) findViewById(R.id.txt_status);
        txt_release = (TextView) findViewById(R.id.txt_release);
        txt_adult = (TextView) findViewById(R.id.txt_adult);
        txt_runtime = (TextView) findViewById(R.id.txt_runtime);
        txt_budget = (TextView) findViewById(R.id.txt_budget);
        txt_revenue = (TextView) findViewById(R.id.txt_revenue);
        txt_overview = (TextView) findViewById(R.id.txt_overview);
        txt_homepage = (TextView) findViewById(R.id.txt_homepage);
        progress = (ProgressBar) findViewById(R.id.progress);
        layout = (CoordinatorLayout) findViewById(R.id.content);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new MovieDetailPresenter(this);
        Intent i = getIntent();
        if (i != null) {
            if (isConnected())
                presenter.onLoadDetail(i.getStringExtra("ID"));
            else
                presenter.onLoadDetailDb(i.getStringExtra("ID"));
        }
    }

    @Override
    public void loadItem(Movie item) {
        Glide.with(this).load(item.getBackdrop_path()).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                centerCrop().crossFade().into(backdrop_path);
        txt_title.setText(item.getTitle());
        txt_status.setText(item.getStatus());
        txt_release.setText(item.getRelease_date());
        if (item.isAdult())
            txt_adult.setVisibility(View.VISIBLE);
        else
            txt_adult.setVisibility(View.INVISIBLE);
        txt_runtime.setText(item.getRunTimeFormat());
        txt_budget.setText(NumberFormat.getCurrencyInstance(
                new Locale("en", "US")).format(item.getBudget()));
        txt_revenue.setText(NumberFormat.getCurrencyInstance(
                new Locale("en", "US")).format(item.getRevenue()));
        txt_overview.setText(item.getOverview());
        txt_homepage.setText(item.getHomepage());
    }

}
