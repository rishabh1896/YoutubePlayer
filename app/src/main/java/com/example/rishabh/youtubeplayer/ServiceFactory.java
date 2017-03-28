package com.example.rishabh.youtubeplayer;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .build();
        final Retrofit restAdapter = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .baseUrl(endPoint)
                .build();

        T service = restAdapter.create(clazz);

        return service;
    }
}
