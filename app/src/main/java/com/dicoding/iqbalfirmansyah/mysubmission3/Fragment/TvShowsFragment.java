package com.dicoding.iqbalfirmansyah.mysubmission3.Fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dicoding.iqbalfirmansyah.mysubmission3.Activity.MainActivity;
import com.dicoding.iqbalfirmansyah.mysubmission3.Adapter.GridMovieAdapter;
import com.dicoding.iqbalfirmansyah.mysubmission3.Adapter.ListMovieAdapter;
import com.dicoding.iqbalfirmansyah.mysubmission3.Connection;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;
import com.dicoding.iqbalfirmansyah.mysubmission3.ViewModel.MainViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment {

    TextView dataTitle, dataOverview;
    ImageView dataPoster;
    ListMovieAdapter listMovieAdapter;
    GridMovieAdapter gridMovieAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    public MainViewModel mainViewModel;
    Connection connection = new Connection();

    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setHasFixedSize(true);

        setHasOptionsMenu(true);
        if (MainActivity.mode == 1){
            showRecyclerList();
        } else {
            showRecyclerGrid();
        }
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getMovie().observe(this, getMovie);

        dataTitle = view.findViewById(R.id.tv_title);
        dataOverview = view.findViewById(R.id.tv_overview);
        dataPoster = view.findViewById(R.id.img_poster);
        progressBar = view.findViewById(R.id.progress_bar);

        if (connection.checkConnection(getContext())){
            mainViewModel.setMovie("tv","en-EN","name", "backdrop_path", "first_air_date");
        }

        return view;
    }

    private Observer<ArrayList<MovieItems>> getMovie = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                if (MainActivity.mode == 1){
                    listMovieAdapter.setData(movieItems);
                } else {
                    gridMovieAdapter.setData(movieItems);
                }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                showLoading(true);
                showRecyclerList();
                if (connection.checkConnection(getContext())){
                    mainViewModel.setMovie("tv","en-EN","name", "backdrop_path", "first_air_date");
                }
                return true;
            case R.id.action_grid:
                showLoading(true);
                showRecyclerGrid();
                if (connection.checkConnection(getContext())){
                    mainViewModel.setMovie("tv","en-EN","name", "backdrop_path", "first_air_date");
                }
                return true;
        }
        return false;
    }
    private void showRecyclerList() {
        listMovieAdapter = new ListMovieAdapter(getContext());
        listMovieAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listMovieAdapter);
    }

    private void showRecyclerGrid() {
        gridMovieAdapter = new GridMovieAdapter(getContext());
        gridMovieAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(gridMovieAdapter);
    }
}
