package com.errorstation.wallpaper.activities;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.errorstation.wallpaper.R;
import com.google.firebase.crash.FirebaseCrash;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    LinearLayout VibrantSwatch,DarkVibrantSwatch,LightVibrantSwatch,MutedSwatch,DarkMutedSwatch,LightMutedSwatch;
    Bitmap theBitmap;
    String link = "https://static.pexels.com/photos/116175/pexels-photo-116175-medium.jpeg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails);
        imageView = (ImageView) findViewById(R.id.testimgV);
        VibrantSwatch = (LinearLayout) findViewById(R.id.color1);
        DarkVibrantSwatch = (LinearLayout) findViewById(R.id.color2);
        LightVibrantSwatch = (LinearLayout) findViewById(R.id.color3);
        MutedSwatch = (LinearLayout) findViewById(R.id.color4);
        DarkMutedSwatch = (LinearLayout) findViewById(R.id.color5);
        LightMutedSwatch = (LinearLayout) findViewById(R.id.color6);
        Intent intent =getIntent();
        link= intent.getStringExtra("thumb");
        if(link!=null) {
            Glide.with(this)
                    .load(link)
                    .override(200, 200)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);


            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                   // Looper.prepare();
                    try {

                        theBitmap = Glide.
                                with(getApplicationContext()).
                                load(link).
                                asBitmap().
                                into(100, 100). // Width and height
                                get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void dummy) {
                    if (null != theBitmap) {
                        Palette.from(theBitmap).maximumColorCount(6).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                // Get the "vibrant" color swatch based on the bitmap
                                Palette.Swatch vibrant1 = palette.getVibrantSwatch();
                                if (vibrant1 != null) {
                                    // Set the background color of a layout based on the vibrant color
                                    VibrantSwatch.setBackgroundColor(vibrant1.getRgb());
                                    int widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
                                    int heightPixels = Resources.getSystem().getDisplayMetrics().heightPixels;
                                    final Bitmap image = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
                                    image.eraseColor(vibrant1.getRgb());

                                    VibrantSwatch.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            WallpaperManager myWallpaperManager
                                                    = WallpaperManager.getInstance(getApplicationContext());
                                            try {
                                                myWallpaperManager.setBitmap(image);
                                            } catch (IOException e) {
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                        }
                                    });


                                }

                                Palette.Swatch vibrant2 = palette.getDarkVibrantSwatch();
                                if (vibrant2 != null) {
                                    // Set the background color of a layout based on the vibrant color
                                    DarkVibrantSwatch.setBackgroundColor(vibrant2.getRgb());
                                }

                                Palette.Swatch vibrant3 = palette.getLightVibrantSwatch();
                                if (vibrant3 != null) {
                                    // Set the background color of a layout based on the vibrant color
                                    LightVibrantSwatch.setBackgroundColor(vibrant3.getRgb());
                                }

                                Palette.Swatch vibrant4 = palette.getMutedSwatch();
                                if (vibrant4 != null) {
                                    // Set the background color of a layout based on the vibrant color
                                    MutedSwatch.setBackgroundColor(vibrant4.getRgb());
                                }


                                Palette.Swatch vibrant5 = palette.getDarkMutedSwatch();
                                if (vibrant5 != null) {
                                    // Set the background color of a layout based on the vibrant color
                                    DarkMutedSwatch.setBackgroundColor(vibrant5.getRgb());
                                }

                                Palette.Swatch vibrant6 = palette.getLightMutedSwatch();
                                if (vibrant6 != null) {
                                    // Set the background color of a layout based on the vibrant color
                                    LightMutedSwatch.setBackgroundColor(vibrant6.getRgb());
                                }
                            }
                        });
// The full bitmap should be available here




                    }
                    ;
                }
            }.execute();
        }







    }

}
