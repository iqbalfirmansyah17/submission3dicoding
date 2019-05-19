package com.dicoding.iqbalfirmansyah.mysubmission3.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    public static final String API_KEY = "96083d49fa7fb99d858f1b3a94ec6d14";
    private MutableLiveData<ArrayList<MovieItems>> listMovies = new MutableLiveData<>();
    private String string1, string2, string3;

    public void setMovie(final String type, final String language, final String title, final String poster, final String date) {
        string1 = title;
        string2 = poster;
        string3 = date;
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieItems> listItems = new ArrayList<>();
        String urlMovie = "https://api.themoviedb.org/3/discover/" + type + "?api_key=" + API_KEY + "&language=" + language;
        client.get(urlMovie, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responObject = new JSONObject(result);
                    JSONArray list = responObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        String title = movie.getString(string1);
                        String overview = movie.getString("overview");
                        String poster = "https://image.tmdb.org/t/p/original" + movie.getString(string2);
                        String popularity = movie.getString("popularity");
                        String releaseDate = movie.getString(string3);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItems.setTitle(title);
                        movieItems.setOverview(overview);
                        movieItems.setPoster(poster);
                        movieItems.setPopularity(popularity);
                        movieItems.setReleaseDate(releaseDate);
                        listItems.add(movieItems);
                    }
                    listMovies.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<MovieItems>> getMovie() {
        return listMovies;
    }
}
