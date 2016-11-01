package com.errorstation.wallpaper.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.Utility;
import com.errorstation.wallpaper.activities.CategoryActivity;
import com.errorstation.wallpaper.adapters.RecyclerAdapter;
import com.errorstation.wallpaper.api.API;
import com.errorstation.wallpaper.api.Wallpaper;
import com.errorstation.wallpaper.api.Wallpaper_;
import com.errorstation.wallpaper.database.FeaturedWallpapersModel;
import com.errorstation.wallpaper.database.WallpaperModel;
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


/**
 * Created by Rubayet on 15-Oct-16.
 */

public class FeaturedFragment extends Fragment {
    View view;
    RecyclerView abstractRV, animalsRV, architectureRV, beachRV, bikesRV, businessRV, cityRV, creativeRV, flowersRV, foodRV, gamesRV, macroRV, natureRV, spaceRV;
    TextView abstractTV, animalsTV, architectureTV, beachTV, bikesTV, businessTV, cityTV, creativeTV, flowersTV, foodTV, gamesTV, macroTV, natureTV, spaceTV;
    int timeDiff = 0;
    int lastTime;
    Realm realm;
    String localTime;
    Activity activity;
    private List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_featured, container, false);
        initializer();
        return view;
    }

    private void initializer() {
        abstractRV = (RecyclerView) view.findViewById(R.id.recyclerView1);
        animalsRV = (RecyclerView) view.findViewById(R.id.recyclerView2);
        architectureRV = (RecyclerView) view.findViewById(R.id.recyclerView3);
        beachRV = (RecyclerView) view.findViewById(R.id.recyclerView4);
        bikesRV = (RecyclerView) view.findViewById(R.id.recyclerView5);
        businessRV = (RecyclerView) view.findViewById(R.id.recyclerView6);
        cityRV = (RecyclerView) view.findViewById(R.id.recyclerView7);
        creativeRV = (RecyclerView) view.findViewById(R.id.recyclerView8);
        flowersRV = (RecyclerView) view.findViewById(R.id.recyclerView11);
        foodRV = (RecyclerView) view.findViewById(R.id.recyclerView12);
        natureRV = (RecyclerView) view.findViewById(R.id.recyclerView9);
        spaceRV = (RecyclerView) view.findViewById(R.id.recyclerView10);
        gamesRV = (RecyclerView) view.findViewById(R.id.recyclerView13);
        macroRV = (RecyclerView) view.findViewById(R.id.recyclerView14);
        abstractTV = (TextView) view.findViewById(R.id.more1TV);
        animalsTV = (TextView) view.findViewById(R.id.more2TV);
        architectureTV = (TextView) view.findViewById(R.id.more3TV);
        beachTV = (TextView) view.findViewById(R.id.more4TV);
        bikesTV = (TextView) view.findViewById(R.id.more5TV);
        businessTV = (TextView) view.findViewById(R.id.more6TV);
        cityTV = (TextView) view.findViewById(R.id.more7TV);
        creativeTV = (TextView) view.findViewById(R.id.more8TV);
        flowersTV = (TextView) view.findViewById(R.id.more11TV);
        foodTV = (TextView) view.findViewById(R.id.more12TV);
        gamesTV = (TextView) view.findViewById(R.id.more13TV);
        macroTV = (TextView) view.findViewById(R.id.more14TV);
        natureTV = (TextView) view.findViewById(R.id.more9TV);
        spaceTV = (TextView) view.findViewById(R.id.more10TV);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity =getActivity();
        onClicklistener();
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+6:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH");
        date.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        localTime = date.format(currentLocalTime);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        lastTime = sharedPref.getInt("Featured", 0);
        timeDiff = Math.abs(Integer.valueOf(localTime) - lastTime);
        Log.d("Diff", String.valueOf(timeDiff));
        Toast.makeText(getActivity(), String.valueOf(timeDiff), Toast.LENGTH_SHORT).show();

        getFeatured();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    private void onClicklistener() {
        abstractTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_abstract));

            }
        });
        animalsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_animals));
            }
        });
        architectureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_architecture));
            }
        });
        beachTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_beach));
            }
        });
        bikesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_bikes));
            }
        });
        businessTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_business));
            }
        });
        cityTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_city));
            }
        });
        creativeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_creative));
            }
        });
        flowersTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_flowers));
            }
        });
        foodTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_food));
            }
        });
        gamesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_games));
            }
        });
        macroTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_macro));
            }
        });
        natureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_nature));
            }
        });
        spaceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTransection(getString(R.string.name_space));
            }
        });
    }

    private void getFeatured() {
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).findAll();

        if (wallpaperModelRealmResults.size() > 0) {
            if (timeDiff >= Utility.UPDATE_INTERVAL) {

                realm.beginTransaction();
                wallpaperModelRealmResults.deleteAllFromRealm();
                realm.commitTransaction();

                API.Factory.getInstance().getFeaturedWallpapers().enqueue(new Callback<Wallpaper>() {
                    @Override
                    public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                        wallpapers = response.body().getWallpaper();

                        for (int i = 0; i < wallpapers.size(); i++) {
                            realm.beginTransaction();

                            FeaturedWallpapersModel wallpaperModel = realm.createObject(FeaturedWallpapersModel.class); // Create a new object

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

                        SharedPreferences sharedPref =getActivity().getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("Featured", Integer.valueOf(localTime));
                        editor.commit();
                        wallpapers.clear();
                        ShowDataFromDB();

                    }

                    @Override
                    public void onFailure(Call<Wallpaper> call, Throwable t) {
                        FirebaseCrash.report(new Exception(t.getMessage()));

                    }
                });
            } else if (timeDiff < Utility.UPDATE_INTERVAL) {


                wallpapers.clear();
                ShowDataFromDB();

            }
        } else if (wallpaperModelRealmResults.size() == 0) {
            API.Factory.getInstance().getFeaturedWallpapers().enqueue(new Callback<Wallpaper>()  {

                @Override
                public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                    wallpapers = response.body().getWallpaper();

                    for (int i = 0; i < wallpapers.size(); i++) {
                        realm.beginTransaction();

                        FeaturedWallpapersModel wallpaperModel = realm.createObject(FeaturedWallpapersModel.class); // Create a new object

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
                    Toast.makeText(getActivity(), "www"+String.valueOf(wallpapers.size()), Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("Featured", Integer.valueOf(localTime));
                    editor.commit();
                    wallpapers.clear();
                    ShowDataFromDB();
                }

                @Override
                public void onFailure(Call<Wallpaper> call, Throwable t) {
                    FirebaseCrash.report(new Exception(t.getMessage()));

                }
            });
        }
    }

    private void ShowDataFromDB() {
        ShowAbstractFeatured();

    }

    private void ShowAbstractFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_ABSTRACT).findAll();


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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            abstractRV.setLayoutManager(layoutManager);
            abstractRV.setAdapter(recyclerAdapter);
        }
        ShowAnimalFeatured();
    }

    private void ShowAnimalFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_ANIMALS).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            animalsRV.setLayoutManager(layoutManager);
            animalsRV.setAdapter(recyclerAdapter);
        }
        ShowArchitectureFeatured();
    }

    private void ShowArchitectureFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_ARCHITECTURE).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            architectureRV.setLayoutManager(layoutManager);
            architectureRV.setAdapter(recyclerAdapter);
        }
        ShowBeachFeatured();
    }

    private void ShowBeachFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_BEACH).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            beachRV.setLayoutManager(layoutManager);
            beachRV.setAdapter(recyclerAdapter);
        }
        ShowBikesFeatured();
    }

    private void ShowBikesFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_BIKES).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            bikesRV.setLayoutManager(layoutManager);
            bikesRV.setAdapter(recyclerAdapter);
        }
        ShowBusinessFeatured();

    }

    private void ShowBusinessFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_BUSINESS).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            businessRV.setLayoutManager(layoutManager);
            businessRV.setAdapter(recyclerAdapter);
        }
        ShowCityFeatured();
    }

    private void ShowCityFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_CITY).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            cityRV.setLayoutManager(layoutManager);
            cityRV.setAdapter(recyclerAdapter);
        }
        ShowCreativeFeatured();
    }

    private void ShowCreativeFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_CREATIVE).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            creativeRV.setLayoutManager(layoutManager);
            creativeRV.setAdapter(recyclerAdapter);
        }
        ShowFlowersFeatured();
    }

    private void ShowFlowersFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_FLOWERS).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            flowersRV.setLayoutManager(layoutManager);
            flowersRV.setAdapter(recyclerAdapter);
        }
        ShowFoodFeatured();
    }

    private void ShowFoodFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_FOOD).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            foodRV.setLayoutManager(layoutManager);
            foodRV.setAdapter(recyclerAdapter);
        }
        ShowGamesFeatured();
    }

    private void ShowGamesFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_GAMES).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            gamesRV.setLayoutManager(layoutManager);
            gamesRV.setAdapter(recyclerAdapter);
        }
        ShowMacroFeatured();
    }

    private void ShowMacroFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_MACRO).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            macroRV.setLayoutManager(layoutManager);
            macroRV.setAdapter(recyclerAdapter);
        }
        ShowNatureFeatured();
    }

    private void ShowNatureFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_NATURE).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            natureRV.setLayoutManager(layoutManager);
            natureRV.setAdapter(recyclerAdapter);
        }
        ShowSpaceFeatured();
    }

    private void ShowSpaceFeatured() {
        List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
        RealmResults<FeaturedWallpapersModel> wallpaperModelRealmResults = realm.where(FeaturedWallpapersModel.class).equalTo(Utility.CATEGORY, Utility.CN_SPACE).findAll();

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

        if (wallpapers.size()>0)
        {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), wallpapers,getActivity());
            LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            spaceRV.setLayoutManager(layoutManager);
            spaceRV.setAdapter(recyclerAdapter);
        }

    }


    private void startTransection(String categoryName) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        intent.putExtra("category", categoryName);
        activity.startActivity(intent);
        activity.finish();
    }
}
