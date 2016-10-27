package com.errorstation.wallpaper.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.activities.CategoryActivity;
import com.errorstation.wallpaper.activities.MainActivity;
import com.errorstation.wallpaper.adapters.RecyclerAdapter;
import com.errorstation.wallpaper.api.API;
import com.errorstation.wallpaper.api.Wallpaper;
import com.errorstation.wallpaper.api.Wallpaper_;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Rubayet on 15-Oct-16.
 */

public class FeaturedFragment extends Fragment {
    View view;
    RecyclerView abstractRV, animalsRV, architectureRV, beachRV, bikesRV, businessRV, cityRV, creativeRV, flowersRV, foodRV, gamesRV, macroRV, natureRV, spaceRV;
    TextView animalsTV;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_featured, container, false);
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
        animalsTV = (TextView) view.findViewById(R.id.more1TV);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getData();
        animalsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CategoryActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getData() {
        getAbstarctFeataured();
    }

    private void getAbstarctFeataured() {
        API.Factory.getInstance().getAbstractWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                abstractRV.setLayoutManager(layoutManager);
                abstractRV.setAdapter(beachAdapter);
                getAnimalFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getAnimalFeataured() {
        API.Factory.getInstance().getAnimalandBirdsWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                animalsRV.setLayoutManager(layoutManager);
                animalsRV.setAdapter(beachAdapter);
                getArchitectureFeataured();


            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getArchitectureFeataured() {
        API.Factory.getInstance().getArchitectureWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                architectureRV.setLayoutManager(layoutManager);
                architectureRV.setAdapter(beachAdapter);
                getBeachFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getBeachFeataured() {
        API.Factory.getInstance().getBeachWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                beachRV.setLayoutManager(layoutManager);
                beachRV.setAdapter(beachAdapter);
                getBikesFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getBikesFeataured() {
        API.Factory.getInstance().getBikeWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                bikesRV.setLayoutManager(layoutManager);
                bikesRV.setAdapter(beachAdapter);
                getBusinessFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getBusinessFeataured() {
        API.Factory.getInstance().getBusinessWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                businessRV.setLayoutManager(layoutManager);
                businessRV.setAdapter(beachAdapter);
                getCityFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getCityFeataured() {
        API.Factory.getInstance().getCityWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                cityRV.setLayoutManager(layoutManager);
                cityRV.setAdapter(beachAdapter);
                getCraetiveFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getCraetiveFeataured() {
        API.Factory.getInstance().getCreativeWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                creativeRV.setLayoutManager(layoutManager);
                creativeRV.setAdapter(beachAdapter);
                getFlowersFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getFlowersFeataured() {
        API.Factory.getInstance().getFlowersWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                flowersRV.setLayoutManager(layoutManager);
                flowersRV.setAdapter(beachAdapter);
                getFoodFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getFoodFeataured() {
        API.Factory.getInstance().getFoodWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                foodRV.setLayoutManager(layoutManager);
                foodRV.setAdapter(beachAdapter);
                getGamesFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getGamesFeataured() {
        API.Factory.getInstance().getGamesWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                gamesRV.setLayoutManager(layoutManager);
                gamesRV.setAdapter(beachAdapter);
                getMacroFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getMacroFeataured() {
        API.Factory.getInstance().getMacroWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                macroRV.setLayoutManager(layoutManager);
                macroRV.setAdapter(beachAdapter);
                getNatureFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getNatureFeataured() {
        API.Factory.getInstance().getNatureWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
               List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                natureRV.setLayoutManager(layoutManager);
                natureRV.setAdapter(beachAdapter);
                getSpaceFeataured();

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void getSpaceFeataured() {
        API.Factory.getInstance().getSpaceWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
             List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers);
                LinearLayoutManager layoutManager; layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                spaceRV.setLayoutManager(layoutManager);
                spaceRV.setAdapter(beachAdapter);
            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }


}
