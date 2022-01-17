package com.uialert.gora.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceNetwork {
    private static ServiceNetwork instance;
    private static final String URL = "https://jsonplaceholder.typicode.com";
    private Retrofit retrofit;

    private ServiceNetwork() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServiceNetwork getInstance() {
        if (instance == null) {
            instance = new ServiceNetwork();
        }
        return instance;
    }

    public JsonplaceholderAPI getAPI() {
        return retrofit.create(JsonplaceholderAPI.class);
    }

}


