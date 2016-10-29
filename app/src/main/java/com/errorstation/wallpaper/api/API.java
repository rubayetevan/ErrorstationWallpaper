package com.errorstation.wallpaper.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rubayet on 24-May-16.
 */
public interface API {

    String baseURL = "http://errorstation.com/admin/";

    @GET("generatejsonv1.php?")
    Call<Wallpaper> getWallpaper(@Query("cat") String category, @Query("user") String userID);



    class Factory {
        public static API api;
        public static OkHttpClient client;

        public static API getInstance() {
            if (api == null) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("VKEY","RXZhbixQb2xpbiZNaXI=")
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });

                client  = httpClient.build();

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseURL)
                        .client(client)
                        .build();
                api = retrofit.create(API.class);
                return api;
            } else {
                return api;
            }
        }
    }
}
