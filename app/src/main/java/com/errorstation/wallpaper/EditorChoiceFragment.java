package com.errorstation.wallpaper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rubayet on 15-Oct-16.
 */

public class EditorChoiceFragment extends Fragment {
    View view;
    GridView grid;
    private List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
    ProgressBar progressBar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_editor_choice,container,false);
        grid = (GridView) view.findViewById(R.id.grid);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);


        progressBar.setVisibility(View.VISIBLE);
        API.Factory.getInstance().getEditorWallpaper().enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(Call<Wallpaper> call, Response<Wallpaper> response) {
                wallpapers = response.body().getWallpaper();
                grid.setAdapter(new GridAdapter(getContext(), wallpapers));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Wallpaper> call, Throwable t) {
                FirebaseCrash.report(new Exception(t.getMessage()));
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
