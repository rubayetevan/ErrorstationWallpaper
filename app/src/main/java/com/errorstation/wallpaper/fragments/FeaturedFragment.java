package com.errorstation.wallpaper.fragments;

import android.app.Activity;
import android.content.Context;
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
    TextView abstractTV, animalsTV, architectureTV, beachTV, bikesTV, businessTV, cityTV, creativeTV, flowersTV, foodTV, gamesTV, macroTV, natureTV, spaceTV;


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
        getData();
        onClicklistener();
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

    private void getData() {
        getAbstarctFeataured();
    }

    private void getAbstarctFeataured() {
        API.Factory.getInstance().getWallpaper(getString(R.string.id_abstract), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_animals), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_architecture), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_beach), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_bikes), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_business), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_city), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_creative), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_flowers), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_food), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_games), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_macro), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_nature), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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
        API.Factory.getInstance().getWallpaper(getString(R.string.id_space), getString(R.string.general_user)).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(), wallpapers, getActivity());
                LinearLayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                spaceRV.setLayoutManager(layoutManager);
                spaceRV.setAdapter(beachAdapter);
            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });
    }

    private void startTransection(String categoryName) {
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        intent.putExtra("category", categoryName);
        startActivity(intent);
    }
}
