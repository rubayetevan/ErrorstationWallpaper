package com.errorstation.wallpaper.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
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
    ImageView backIMGV;
    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializer();
        getData(categoryName);


    }

    private void initializer() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        categoryName = intent.getStringExtra("category");

        getSupportActionBar().setTitle(categoryName);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);

        grid = (GridView) findViewById(R.id.grid2);
        backIMGV = (ImageView) findViewById(R.id.backIMGV);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#E91E63"), android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setVisibility(View.VISIBLE);
        ViewCompat.setNestedScrollingEnabled(grid, true);

        backIMGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getData(String categoryName) {
        if(categoryName.matches(getString(R.string.name_animals))) {
            API.Factory.getInstance().getAnimalandBirdsWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        else if (categoryName.matches(getString(R.string.name_abstract)))
        {
            API.Factory.getInstance().getAbstractWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        else if (categoryName.matches(getString(R.string.name_architecture)))
        {
            API.Factory.getInstance().getArchitectureWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        if(categoryName.matches(getString(R.string.name_beach))) {
            API.Factory.getInstance().getBeachWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        else if (categoryName.matches(getString(R.string.name_bikes)))
        {
            API.Factory.getInstance().getBikeWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        else if (categoryName.matches(getString(R.string.name_business)))
        {
            API.Factory.getInstance().getBusinessWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        if(categoryName.matches(getString(R.string.name_city))) {
            API.Factory.getInstance().getCityWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        else if (categoryName.matches(getString(R.string.name_creative)))
        {
            API.Factory.getInstance().getCreativeWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        else if (categoryName.matches(getString(R.string.name_flowers)))
        {
            API.Factory.getInstance().getFlowersWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        if(categoryName.matches(getString(R.string.name_food))) {
            API.Factory.getInstance().getFoodWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        else if (categoryName.matches(getString(R.string.name_games)))
        {
            API.Factory.getInstance().getGamesWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        else if (categoryName.matches(getString(R.string.name_macro)))
        {
            API.Factory.getInstance().getMacroWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
        else if (categoryName.matches(getString(R.string.name_nature)))
        {
            API.Factory.getInstance().getNatureWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        else if (categoryName.matches(getString(R.string.name_space)))
        {
            API.Factory.getInstance().getSpaceWallpaper().enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        startActivity(intent);
        finish();
    }
}
