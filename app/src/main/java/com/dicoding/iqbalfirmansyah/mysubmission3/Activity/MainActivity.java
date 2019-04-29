package com.dicoding.iqbalfirmansyah.mysubmission3.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dicoding.iqbalfirmansyah.mysubmission3.Fragment.MoviesFragment;
import com.dicoding.iqbalfirmansyah.mysubmission3.Fragment.TvShowsFragment;
import com.dicoding.iqbalfirmansyah.mysubmission3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_movies);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    fragment = new MoviesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment,
                            fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_tv_shows:
                    fragment = new TvShowsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, fragment,
                            fragment.getClass().getSimpleName()).commit();
                    return true;
            }
            return false;
        }
    };
}
