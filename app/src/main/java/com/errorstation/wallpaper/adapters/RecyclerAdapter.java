package com.errorstation.wallpaper.adapters;

import android.content.Context;
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

    public RecyclerAdapter(Context context, List<Wallpaper_> wallpapers) {
        this.context = context;
        this.wallpapers = wallpapers;
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
    public void onBindViewHolder(WallpaperHolder holder, int position) {
        final String thumb = wallpapers.get(position).getThumb();


        Glide.with(context)
                .load(thumb)
                .override(200, 200)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        FirebaseCrash.report(new Exception(e.toString()));
                        FirebaseCrash.log("imageURL: " + thumb);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.wallpaperView);
    }

    @Override
    public int getItemCount() {
        return wallpapers.size();
    }
}
