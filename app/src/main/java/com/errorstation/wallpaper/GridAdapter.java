package com.errorstation.wallpaper;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.analytics.HitBuilders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rubayet on 15-Oct-16.
 */

class GridAdapter extends BaseAdapter

{
    FirebaseAnalytics mFirebaseAnalytics;
    Context context;
    List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
    LayoutInflater inflater;


    public GridAdapter(Context context, List<Wallpaper_> wallpapers) {
        this.context = context;
        this.wallpapers = wallpapers;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);

    }

    @Override
    public int getCount() {
        return wallpapers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.grid_item, parent, false);
        holder.img = (ImageView) convertView.findViewById(R.id.imgv);

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
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, DetailsActivity.class);


                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, wallpapers.get(position).getCategory());
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, thumb);
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
                intent.putExtra("thumb", thumb);
                intent.putExtra("link", wallpapers.get(position).getPicurl());
                intent.putExtra("rating", wallpapers.get(position).getRating());
                intent.putExtra("title", wallpapers.get(position).getTitle());
                intent.putExtra("description", wallpapers.get(position).getDescription());
                intent.putExtra("source", wallpapers.get(position).getSource());
                intent.putExtra("category", wallpapers.get(position).getCategory());
                intent.putExtra("picID", wallpapers.get(position).getPicid());
                intent.putExtra("view",wallpapers.get(position).getViews());


                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    GoogleAnalyticsData.tracker().send(new HitBuilders.EventBuilder(thumb, "Preview")
                            .setLabel("Image")
                            .build());
                    Bundle bundle1 = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, holder.img, holder.img.getTransitionName()).toBundle();
                    startActivity(intent, bundle1);
                } else {*/
                    /*googleAnalyticsData.tracker().send(new HitBuilders.EventBuilder(thumb, "thumb_Preview")
                            .setLabel("Image")
                            .build());
*/
                    context.startActivity(intent);
               // }


            }
        });

        //setAnimation(holder.img,position);
        return convertView;
    }

    public class Holder {
        ImageView img;

    }


}