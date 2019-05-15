package com.dicoding.iqbalfirmansyah.mysubmission3.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dicoding.iqbalfirmansyah.mysubmission3.Adapter.ListMovieAdapter;
import com.dicoding.iqbalfirmansyah.mysubmission3.Connection;
import com.dicoding.iqbalfirmansyah.mysubmission3.Loader.MyAsyncTaskLoader;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    TextView dataTitle, dataOverview;
    ImageView dataPoster;
    ListMovieAdapter listMovieAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);
        recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setHasFixedSize(true);

        listMovieAdapter = new ListMovieAdapter(getContext());
        listMovieAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listMovieAdapter);

        dataTitle = view.findViewById(R.id.tv_title);
        dataOverview = view.findViewById(R.id.tv_overview);
        dataPoster = view.findViewById(R.id.img_poster);
        progressBar = view.findViewById(R.id.progress_bar);

        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);

        Connection connection = new Connection();
        if (connection.checkConnection(getContext())) {
            getLoaderManager().initLoader(0, null, this);
        }
        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, @Nullable Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new MyAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        listMovieAdapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {
        listMovieAdapter.setData(null);
    }
}
