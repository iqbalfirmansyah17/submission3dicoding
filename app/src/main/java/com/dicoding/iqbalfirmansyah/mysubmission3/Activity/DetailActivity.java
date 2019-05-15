package com.dicoding.iqbalfirmansyah.mysubmission3.Activity;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.iqbalfirmansyah.mysubmission3.Connection;
import com.dicoding.iqbalfirmansyah.mysubmission3.Loader.MyAsyncTaskLoader;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    TextView dataTitle, dataOverview, dataPopularity, dataReleaseDate;
    ImageView dataPoster;
    View layoutBeside, layoutBelow;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (MainActivity.EXTRA_LOCAL != null) {
            PreferenceActivity preferenceActivity = new PreferenceActivity();
            preferenceActivity.setLocale(this, MainActivity.EXTRA_LOCAL);
        }

        dataTitle = findViewById(R.id.tv_title);
        dataOverview = findViewById(R.id.tv_overview_detail);
        dataPopularity = findViewById(R.id.tv_popularity);
        dataReleaseDate = findViewById(R.id.tv_release_date);
        dataPoster = findViewById(R.id.img_poster_detail);
        layoutBelow = findViewById(R.id.layout_below);
        layoutBeside = findViewById(R.id.layout_beside);

        progressBar = findViewById(R.id.progress_bar);

        dataTitle.setVisibility(View.INVISIBLE);
        layoutBeside.setVisibility(View.INVISIBLE);
        layoutBelow.setVisibility(View.INVISIBLE);
        dataPoster.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);

        Connection connection = new Connection();
        if (connection.checkConnection(this)) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, @Nullable Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new MyAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        int position = getIntent().getIntExtra("EXTRA_POSITION", -1);
        dataTitle.setText(movieItems.get(position).getTitle());
        dataOverview.setText(movieItems.get(position).getOverview());
        dataPopularity.setText(movieItems.get(position).getPopularity());
        dataReleaseDate.setText(movieItems.get(position).getReleaseDate());
        Glide.with(this)
                .load(movieItems.get(position).getPoster())
                .apply(new RequestOptions())
                .into(dataPoster);

        dataTitle.setVisibility(View.VISIBLE);
        layoutBeside.setVisibility(View.VISIBLE);
        layoutBelow.setVisibility(View.VISIBLE);
        dataPoster.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {
    }
}

