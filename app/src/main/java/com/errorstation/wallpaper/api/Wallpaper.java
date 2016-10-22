
package com.errorstation.wallpaper.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class Wallpaper {

    @SerializedName("wallpaper")
    @Expose
    private List<Wallpaper_> wallpaper = new ArrayList<Wallpaper_>();

    /**
     * 
     * @return
     *     The wallpaper
     */
    public List<Wallpaper_> getWallpaper() {
        return wallpaper;
    }

    /**
     * 
     * @param wallpaper
     *     The wallpaper
     */
    public void setWallpaper(List<Wallpaper_> wallpaper) {
        this.wallpaper = wallpaper;
    }

    /**
     * Created by Rubayet on 03-Oct-16.
     */

    public static interface DownloadAndView {

        String baseURL = "http://errorstation.com/admin/";

        @GET("viewdownload.php?")
        Call<DorV> CountDownloadOrView(@Query("pid") String pid, @Query("action") String action);

        class Factory {
            public static DownloadAndView downloadAndView;

            public static DownloadAndView getInstance() {
                if (downloadAndView == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(baseURL)
                            .build();
                    downloadAndView = retrofit.create(DownloadAndView.class);
                    return downloadAndView;
                } else {
                    return downloadAndView;
                }
            }
        }
    }
}
