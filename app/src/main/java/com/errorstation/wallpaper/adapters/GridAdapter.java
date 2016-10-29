package com.errorstation.wallpaper.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import com.errorstation.wallpaper.activities.DetailsActivity;
import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.api.Wallpaper_;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rubayet on 15-Oct-16.
 */

public class GridAdapter extends BaseAdapter

{
    FirebaseAnalytics mFirebaseAnalytics;
    Context context;
    List<Wallpaper_> wallpapers = new ArrayList<Wallpaper_>();
    LayoutInflater inflater;
    double widthPixels, heightIndividual, widthIndividual;
    Activity activity;


    public GridAdapter(Context context, List<Wallpaper_> wallpapers, Activity activity) {
        this.context = context;
        this.wallpapers = wallpapers;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        this.activity = activity;
        widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
        widthIndividual = (widthPixels / 2.0);
        heightIndividual = ((420.0 / 345.0) * widthIndividual);


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
        holder.titleTV = (TextView) convertView.findViewById(R.id.gtitleTV);
        final String thumb = wallpapers.get(position).getThumb();

        ViewGroup.LayoutParams params = holder.img.getLayoutParams();
// Changes the height and width to the specified *pixels*
        params.height = (int) heightIndividual;
        params.width = (int) (widthIndividual);
        holder.img.setLayoutParams(params);
        holder.titleTV.setText(wallpapers.get(position).getTitle());
        Glide.with(context)
                .load(thumb)
                .override((int) widthIndividual, (int) heightIndividual)
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
                intent.putExtra("view", wallpapers.get(position).getViews());


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Bundle bundle1 = ActivityOptions.makeSceneTransitionAnimation(activity, holder.img, holder.img.getTransitionName()).toBundle();
                    context.startActivity(intent, bundle1);
                } else {
                context.startActivity(intent);
                 }


            }
        });

        //setAnimation(holder.img,position);
        return convertView;
    }

    public class Holder {
        ImageView img;
        TextView titleTV;

    }


}