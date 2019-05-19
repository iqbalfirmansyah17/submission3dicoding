package com.dicoding.iqbalfirmansyah.mysubmission3.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.iqbalfirmansyah.mysubmission3.Connection;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;
import com.dicoding.iqbalfirmansyah.mysubmission3.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.Locale;

import static com.dicoding.iqbalfirmansyah.mysubmission3.Activity.MainActivity.EXTRA_LOCAL;

public class DetailActivity extends AppCompatActivity {

    TextView dataTitle, dataOverview, dataPopularity, dataReleaseDate;
    ImageView dataPoster;
    View layoutBeside, layoutBelow;
    ProgressBar progressBar;

    public MainViewModel mainViewModel;
    Connection connection = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMovie().observe(this, getMovie);

        dataTitle = findViewById(R.id.tv_title);
        dataOverview = findViewById(R.id.tv_overview_detail);
        dataPopularity = findViewById(R.id.tv_popularity);
        dataReleaseDate = findViewById(R.id.tv_release_date);
        dataPoster = findViewById(R.id.img_poster_detail);
        layoutBelow = findViewById(R.id.layout_below);
        layoutBeside = findViewById(R.id.layout_beside);
        progressBar = findViewById(R.id.progress_bar);

        dataTitle.setVisibility(View.GONE);
        dataPoster.setVisibility(View.GONE);
        dataPopularity.setVisibility(View.GONE);
        dataReleaseDate.setVisibility(View.GONE);
        dataOverview.setVisibility(View.GONE);
        layoutBelow.setVisibility(View.GONE);
        layoutBeside.setVisibility(View.GONE);

        if (connection.checkConnection(getApplicationContext())) {
            if (MainActivity.ID_TAB == 101) {
                mainViewModel.setMovie("movie", "en-EN", "title", "poster_path", "release_date");
            } else if (MainActivity.ID_TAB == 102) {
                mainViewModel.setMovie("tv", "en-EN", "name", "backdrop_path", "first_air_date");
            }
        }
    }

    private Observer<ArrayList<MovieItems>> getMovie = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                int position = getIntent().getIntExtra("EXTRA_POSITION", -1);
                dataTitle.setText(movieItems.get(position).getTitle());
                dataOverview.setText(movieItems.get(position).getOverview());
                dataPopularity.setText(movieItems.get(position).getPopularity());
                dataReleaseDate.setText(movieItems.get(position).getReleaseDate());
                Glide.with(getApplicationContext())
                        .load(movieItems.get(position).getPoster())
                        .apply(new RequestOptions())
                        .into(dataPoster);
                dataTitle.setVisibility(View.VISIBLE);
                dataPoster.setVisibility(View.VISIBLE);
                dataPopularity.setVisibility(View.VISIBLE);
                dataReleaseDate.setVisibility(View.VISIBLE);
                dataOverview.setVisibility(View.VISIBLE);
                layoutBelow.setVisibility(View.VISIBLE);
                layoutBeside.setVisibility(View.VISIBLE);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Configuration config = getBaseContext().getResources().getConfiguration();
//        config.locale = Locale.getDefault();
//        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
//    }
}

