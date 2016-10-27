package com.errorstation.wallpaper.activities;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.adapters.GridAdapter;
import com.errorstation.wallpaper.api.API;
import com.errorstation.wallpaper.api.Wallpaper;
import com.errorstation.wallpaper.api.Wallpaper_;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    GridView grid;
    private List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
    ProgressBar progressBar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        grid = (GridView) findViewById(R.id.grid2);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#E91E63"), android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.VISIBLE);
        ViewCompat.setNestedScrollingEnabled(grid,true);
        getSupportActionBar().setTitle("Animal Wallpaper");

        API.Factory.getInstance().getAnimalandBirdsWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                wallpapers = response.body().getWallpaper();
                grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));
                progressBar.setVisibility(View.GONE);
            }
        });


    }
}
