package com.dicoding.iqbalfirmansyah.mysubmission3.Loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.dicoding.iqbalfirmansyah.mysubmission3.Activity.MainActivity;
import com.dicoding.iqbalfirmansyah.mysubmission3.Activity.PreferenceActivity;
import com.dicoding.iqbalfirmansyah.mysubmission3.Model.MovieItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {
    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    public MyAsyncTaskLoader(final Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "96083d49fa7fb99d858f1b3a94ec6d14";
    private String type, string1, string2, string3, language;

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        if (PreferenceActivity.ID_LANGUAGE == 101) {
            language = "en-EN";
        } else if (PreferenceActivity.ID_LANGUAGE == 102){
            language = "ru-RU";
        }
        if (MainActivity.ID_TAB == 101) {
            type = "movie";
            string1 = "title";
            string2 = "poster_path";
            string3 = "release_date";
        } else if (MainActivity.ID_TAB == 102) {
            type = "tv";
            string1 = "name";
            string2 = "backdrop_path";
            string3 = "first_air_date";
        }
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
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
                        movieItemses.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }
}
