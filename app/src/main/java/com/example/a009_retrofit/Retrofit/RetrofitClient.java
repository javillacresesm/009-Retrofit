package com.example.a009_retrofit.Retrofit;

import com.example.a009_retrofit.RetrofitApi.RetrofitApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static RetrofitApi instance;

    public static RetrofitApi getRetrofitClient() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(RetrofitApi.class);
        }
        return instance;
    }
}
