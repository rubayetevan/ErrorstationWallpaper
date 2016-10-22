package com.errorstation.wallpaper.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.errorstation.wallpaper.R;
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
    RecyclerView beachRV;
    private List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_featured,container,false);
        beachRV = (RecyclerView) view.findViewById(R.id.recyclerView1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        API.Factory.getInstance().getSpaceWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                wallpapers.clear();
                wallpapers = response.body().getWallpaper();
                RecyclerAdapter beachAdapter = new RecyclerAdapter(getContext(),wallpapers);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                beachRV.setLayoutManager(layoutManager);

                beachRV.setAdapter(beachAdapter);

            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));

            }
        });


    }
}
