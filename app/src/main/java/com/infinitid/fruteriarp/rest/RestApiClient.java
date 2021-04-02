package com.infinitid.fruteriarp.rest;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build();

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("192.168.0.109:8080/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        }
        return retrofit;
    }
}