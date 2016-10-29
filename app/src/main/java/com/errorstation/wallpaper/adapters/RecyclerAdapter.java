package com.errorstation.wallpaper.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.activities.DetailsActivity;
import com.errorstation.wallpaper.api.Wallpaper_;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rubayet on 22-Oct-16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.WallpaperHolder> {
    Context context;
    List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
    Activity activity;

    public RecyclerAdapter(Context context, List<Wallpaper_> wallpapers,Activity activity) {
        this.context = context;
        this.wallpapers = wallpapers;
        this.activity =activity;
    }

    public class WallpaperHolder extends RecyclerView.ViewHolder {

        public ImageView wallpaperView;

        public WallpaperHolder(View itemView) {
            super(itemView);
            wallpaperView = (ImageView) itemView.findViewById(R.id.rimgv);
        }
    }

    @Override
    public WallpaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new WallpaperHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WallpaperHolder holder, int position) {
        final String thumb = wallpapers.get(position).getThumb();
        final Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("thumb", thumb);
        intent.putExtra("link", wallpapers.get(position).getPicurl());
        intent.putExtra("rating", wallpapers.get(position).getRating());
        intent.putExtra("title", wallpapers.get(position).getTitle());
        intent.putExtra("description", wallpapers.get(position).getDescription());
        intent.putExtra("source", wallpapers.get(position).getSource());
        intent.putExtra("category", wallpapers.get(position).getCategory());
        intent.putExtra("picID", wallpapers.get(position).getPicid());
        intent.putExtra("view", wallpapers.get(position).getViews());

        Glide.with(context)
                .load(thumb)
                .override(200, 200)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        FirebaseCrash.report(new Exception(e.getMessage()));
                        FirebaseCrash.log("imageURL: " + thumb);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.wallpaperView);
        holder.wallpaperView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Bundle bundle1 = ActivityOptions.makeSceneTransitionAnimation(activity, holder.wallpaperView, holder.wallpaperView.getTransitionName()).toBundle();
                    context.startActivity(intent, bundle1);
                } else {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }
}
