package com.errorstation.wallpaper.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.Utility;
import com.errorstation.wallpaper.adapters.GridAdapter;
import com.errorstation.wallpaper.api.API;
import com.errorstation.wallpaper.api.Wallpaper;
import com.errorstation.wallpaper.api.Wallpaper_;

import com.errorstation.wallpaper.database.WallpaperModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmResults;
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
    int timeDiff = 0;
    int lastTime;
    Realm realm;
    String localTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+6:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        date.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        localTime = date.format(currentLocalTime);

        initializer();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        lastTime = sharedPref.getInt(categoryName, 0);
        timeDiff = Math.abs(Integer.valueOf(localTime) - lastTime);
        Log.d("Diff", String.valueOf(timeDiff));
        //Toast.makeText(this, String.valueOf(timeDiff), Toast.LENGTH_SHORT).show();
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
            }
        });
    }

    private void getData(String categoryName) {
        if (categoryName.matches(getString(R.string.name_animals))) {
            getWallpaperFromServer(getString(R.string.id_animals), getString(R.string.name_animals));
        } else if (categoryName.matches(getString(R.string.name_abstract))) {
            getWallpaperFromServer(getString(R.string.id_abstract), getString(R.string.name_abstract));
        } else if (categoryName.matches(getString(R.string.name_architecture))) {
            getWallpaperFromServer(getString(R.string.id_architecture), getString(R.string.name_architecture));
        } else if (categoryName.matches(getString(R.string.name_beach))) {
            getWallpaperFromServer(getString(R.string.id_beach), getString(R.string.name_beach));
        } else if (categoryName.matches(getString(R.string.name_bikes))) {
            getWallpaperFromServer(getString(R.string.id_bikes), getString(R.string.name_bikes));
        } else if (categoryName.matches(getString(R.string.name_business))) {
            getWallpaperFromServer(getString(R.string.id_business), getString(R.string.name_business));
        } else if (categoryName.matches(getString(R.string.name_city))) {
            getWallpaperFromServer(getString(R.string.id_city), getString(R.string.name_city));
        } else if (categoryName.matches(getString(R.string.name_creative))) {
            getWallpaperFromServer(getString(R.string.id_creative), getString(R.string.name_creative));
        } else if (categoryName.matches(getString(R.string.name_flowers))) {
            getWallpaperFromServer(getString(R.string.id_flowers), getString(R.string.name_flowers));
        } else if (categoryName.matches(getString(R.string.name_food))) {
            getWallpaperFromServer(getString(R.string.id_food), getString(R.string.name_food));
        } else if (categoryName.matches(getString(R.string.name_games))) {
            getWallpaperFromServer(getString(R.string.id_games), getString(R.string.name_games));
        } else if (categoryName.matches(getString(R.string.name_macro))) {
            getWallpaperFromServer(getString(R.string.id_macro), getString(R.string.name_macro));
        } else if (categoryName.matches(getString(R.string.name_nature))) {
            getWallpaperFromServer(getString(R.string.id_nature), getString(R.string.name_nature));
        } else if (categoryName.matches(getString(R.string.name_space))) {
            getWallpaperFromServer(getString(R.string.id_space), getString(R.string.name_space));
        }

    }

    private void getWallpaperFromServer(String categoryID, final String categoryName) {

        RealmResults<WallpaperModel> wallpaperModelRealmResults = realm.where(WallpaperModel.class)
                .equalTo("category", categoryName)
                .findAll();
        if (wallpaperModelRealmResults.size() > 0) {
            if (timeDiff >= Utility.UPDATE_INTERVAL) {

                realm.beginTransaction();
                wallpaperModelRealmResults.deleteAllFromRealm();
                realm.commitTransaction();

                API.Factory.getInstance().getWallpaper(categoryID, getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
                    @Override
                    public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                        wallpapers = response.body().getWallpaper();
                        if (wallpapers.get(0).getCategory().matches(categoryName)) {
                           // Toast.makeText(CategoryActivity.this, "True", Toast.LENGTH_LONG).show();
                        }

                        for (int i = 0; i < wallpapers.size(); i++) {
                            realm.beginTransaction();

                            WallpaperModel wallpaperModel = realm.createObject(WallpaperModel.class); // Create a new object

                            wallpaperModel.setCategory(wallpapers.get(i).getCategory());
                            wallpaperModel.setDescription(wallpapers.get(i).getDescription());
                            wallpaperModel.setDownloads(wallpapers.get(i).getDownloads());
                            wallpaperModel.setSource(wallpapers.get(i).getSource());
                            wallpaperModel.setLiked(wallpapers.get(i).getLiked());
                            wallpaperModel.setPicid(wallpapers.get(i).getPicid());
                            wallpaperModel.setPicurl(wallpapers.get(i).getPicurl());
                            wallpaperModel.setThumb(wallpapers.get(i).getThumb());
                            wallpaperModel.setTitle(wallpapers.get(i).getTitle());
                            wallpaperModel.setRating(wallpapers.get(i).getRating());
                            wallpaperModel.setViews(wallpapers.get(i).getViews());

                            realm.commitTransaction();
                        }
                        grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));


                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(categoryName, Integer.valueOf(localTime));
                        editor.commit();

                    }

                    @Override
                    public void onFailure(Call<Wallpaper> call, Throwable t) {
                        FirebaseCrash.report(new Exception(t.getMessage()));
                        progressBar.setVisibility(View.GONE);
                    }
                });
            } else if (timeDiff < Utility.UPDATE_INTERVAL) {
               // Toast.makeText(CategoryActivity.this, "from DB", Toast.LENGTH_LONG).show();

                for (int i = 0; i < wallpaperModelRealmResults.size(); i++) {
                    Wallpaper_ wallpaper_ = new Wallpaper_();

                    wallpaper_.setCategory(wallpaperModelRealmResults.get(i).getCategory());
                    wallpaper_.setDescription(wallpaperModelRealmResults.get(i).getDescription());
                    wallpaper_.setDownloads(wallpaperModelRealmResults.get(i).getDownloads());
                    wallpaper_.setSource(wallpaperModelRealmResults.get(i).getSource());
                    wallpaper_.setLiked(wallpaperModelRealmResults.get(i).getLiked());
                    wallpaper_.setPicid(wallpaperModelRealmResults.get(i).getPicid());
                    wallpaper_.setPicurl(wallpaperModelRealmResults.get(i).getPicurl());
                    wallpaper_.setThumb(wallpaperModelRealmResults.get(i).getThumb());
                    wallpaper_.setTitle(wallpaperModelRealmResults.get(i).getTitle());
                    wallpaper_.setRating(wallpaperModelRealmResults.get(i).getRating());
                    wallpaper_.setViews(wallpaperModelRealmResults.get(i).getViews());

                    wallpapers.add(i, wallpaper_);
                }
                grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));

            }
        } else if (wallpaperModelRealmResults.size() == 0) {
            API.Factory.getInstance().getWallpaper(categoryID, getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();
              //      Toast.makeText(CategoryActivity.this, wallpapers.get(0).getCategory(), Toast.LENGTH_LONG).show();
                    for (int i = 0; i < wallpapers.size(); i++) {
                        realm.beginTransaction();

                        WallpaperModel wallpaperModel = realm.createObject(WallpaperModel.class); // Create a new object

                        wallpaperModel.setCategory(wallpapers.get(i).getCategory());
                        wallpaperModel.setDescription(wallpapers.get(i).getDescription());
                        wallpaperModel.setDownloads(wallpapers.get(i).getDownloads());
                        wallpaperModel.setSource(wallpapers.get(i).getSource());
                        wallpaperModel.setLiked(wallpapers.get(i).getLiked());
                        wallpaperModel.setPicid(wallpapers.get(i).getPicid());
                        wallpaperModel.setPicurl(wallpapers.get(i).getPicurl());
                        wallpaperModel.setThumb(wallpapers.get(i).getThumb());
                        wallpaperModel.setTitle(wallpapers.get(i).getTitle());
                        wallpaperModel.setRating(wallpapers.get(i).getRating());
                        wallpaperModel.setViews(wallpapers.get(i).getViews());

                        realm.commitTransaction();
                    }
                    grid.setAdapter(new GridAdapter(CategoryActivity.this, wallpapers, CategoryActivity.this));

                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(categoryName, Integer.valueOf(localTime));
                    editor.commit();

                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        progressBar.setVisibility(View.GONE);
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
