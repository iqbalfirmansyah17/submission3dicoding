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
import android.widget.TextView;

import com.dicoding.iqbalfirmansyah.mysubmission3.Adapter.MoviesAdapter;
import com.dicoding.iqbalfirmansyah.mysubmission3.Loader.MyAsyncTaskLoader;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    TextView dataTitle, dataOverview;
    ImageView dataPoster;
    MoviesAdapter moviesAdapter;
    RecyclerView recyclerView;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(getContext());
        moviesAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(moviesAdapter);

        dataTitle = view.findViewById(R.id.tv_title);
        dataOverview = view.findViewById(R.id.tv_overview);
        dataPoster = view.findViewById(R.id.img_poster);

        getLoaderManager().initLoader(0, null, this);

        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, @Nullable Bundle args) {
        return new MyAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        moviesAdapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {
        moviesAdapter.setData(null);
    }
}
