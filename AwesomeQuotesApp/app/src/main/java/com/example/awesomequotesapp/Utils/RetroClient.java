package com.example.awesomequotesapp.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient
{
    private static  RetroClient retroClient = null;

    private API api;

    public RetroClient() {

        api = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API.BASE_URL).build().create(API.class);

    }

    public static RetroClient getInstance()
    {
        if(retroClient == null)
        {
            retroClient = new RetroClient();
        }
        return retroClient;
    }

    public API getApi() {
        return api;
    }
}
