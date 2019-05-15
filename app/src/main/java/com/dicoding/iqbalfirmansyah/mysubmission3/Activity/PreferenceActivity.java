package com.dicoding.iqbalfirmansyah.mysubmission3.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

    public final int LANGUAGE_EN = 101;
    public final int LANGUAGE_RU = 102;

    public static int ID_LANGUAGE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        getSupportActionBar().setTitle(R.string.language);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnSetLanguage = findViewById(R.id.btn_set_language);
        rgLanguage = findViewById(R.id.rg_language);

        btnSetLanguage.setOnClickListener(this);
    }

    public void setLocale(Activity activity, String language) {
        Locale myLocale = new Locale(language);
        Resources resources = activity.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = myLocale;
        resources.updateConfiguration(configuration, displayMetrics);

    }

//    public static void changeLocale(Activity activity, String language)
//    {
//        final Resources res = activity.getResources();
//        final Configuration conf = res.getConfiguration();
//        if (language == null || language.length() == 0)
//        {
//            conf.locale = Locale.getDefault();
//        }
//        else
//        {
//            final int idx = language.indexOf('-');
//            if (idx != -1)
//            {
//                final String[] split = language.split("-");
//                conf.locale = new Locale(split[0], split[1].substring(1));
//            }
//            else
//            {
//                conf.locale = new Locale(language);
//            }
//        }
//        res.updateConfiguration(conf, null);
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_language) {
            if (rgLanguage.getCheckedRadioButtonId() != 0) {
                Intent refresh = new Intent(this, MainActivity.class);
                switch (rgLanguage.getCheckedRadioButtonId()) {
                    case R.id.rb_english:
                        ID_LANGUAGE = LANGUAGE_EN;
                        finish();
                        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        refresh.putExtra(MainActivity.EXTRA_LOCAL, "en");
                        startActivity(refresh);
                        Toast.makeText(getApplicationContext(), "Success change language to english ...",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_rusia:
                        ID_LANGUAGE = LANGUAGE_RU;
                        finish();
                        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        refresh.putExtra(MainActivity.EXTRA_LOCAL, "in");
                        startActivity(refresh);
                        Toast.makeText(getApplicationContext(), "\n" + "Успех смени язык на русью ...",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
}
