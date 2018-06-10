package com.adetech.simila.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance
{
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=sanmi&api_key=f1206ed0cd61663480d26f89d76d622b&format=json";
    public static Retrofit getRetrofitInstance()
    {
        if (sRetrofit == null)
        {
            sRetrofit = new retrofit2.Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return  sRetrofit;
    }
}
