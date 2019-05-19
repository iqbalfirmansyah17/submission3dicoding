package com.dicoding.iqbalfirmansyah.mysubmission3.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dicoding.iqbalfirmansyah.mysubmission3.R;

import java.util.Locale;

import static com.dicoding.iqbalfirmansyah.mysubmission3.Activity.MainActivity.EXTRA_LOCAL;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSetLanguage;
    RadioGroup rgLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.language);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnSetLanguage = findViewById(R.id.btn_set_language);
        rgLanguage = findViewById(R.id.rg_language);

        btnSetLanguage.setOnClickListener(this);
    }

    public void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_language) {
            if (rgLanguage.getCheckedRadioButtonId() != 0) {
                Intent refresh = new Intent(this, MainActivity.class);
                switch (rgLanguage.getCheckedRadioButtonId()) {
                    case R.id.rb_english:
                        finish();
                        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        refresh.putExtra(EXTRA_LOCAL, "en");
                        setLocale("en");
                        startActivity(refresh);
                        Toast.makeText(getApplicationContext(), "Success change language to english ...",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_indonesia:
                        finish();
                        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        refresh.putExtra(EXTRA_LOCAL, "in");
                        setLocale("in");
                        startActivity(refresh);
                        Toast.makeText(getApplicationContext(), "Sukses mengubah bahasa ke bahasa indonesia ...",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
}
