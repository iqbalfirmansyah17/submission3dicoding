package com.dicoding.iqbalfirmansyah.mysubmission3.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.iqbalfirmansyah.mysubmission3.Fragment.MoviesFragment;
import com.dicoding.iqbalfirmansyah.mysubmission3.Fragment.TvShowsFragment;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;

public class MainActivity extends AppCompatActivity {

    public final int TAB_MOVIES = 101;
    public final int TAB_TV_SHOW = 102;

    public static int ID_TAB = 0;

    public static String EXTRA_LOCAL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (EXTRA_LOCAL != null) {
            PreferenceActivity preferenceActivity = new PreferenceActivity();
            preferenceActivity.setLocale(this, EXTRA_LOCAL);
        }
        getSupportActionBar().setTitle(R.string.app_name);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_movies);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        if (EXTRA_LOCAL != null) {
            PreferenceActivity preferenceActivity = new PreferenceActivity();
            preferenceActivity.setLocale(this, EXTRA_LOCAL);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_language:
                Intent intent = new Intent(this, PreferenceActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_list:
                return false;
            case R.id.action_grid:
                return false;
            case R.id.action_cardview:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    ID_TAB = TAB_MOVIES;
                    fragment = new MoviesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment,
                            fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_tv_shows:
                    ID_TAB = TAB_TV_SHOW;
                    fragment = new TvShowsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment,
                            fragment.getClass().getSimpleName()).commit();
                    return true;
            }
            return false;
        }
    };
}
