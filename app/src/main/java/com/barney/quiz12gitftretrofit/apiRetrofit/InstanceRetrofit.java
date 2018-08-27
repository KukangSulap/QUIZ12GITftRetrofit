package com.barney.quiz12gitftretrofit.apiRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceRetrofit {
    private static final String Weburl = "http://muslimsalat.com/jakarta/daily.json?key=d1aa7cdf5e8c0d2d7f1f47811497a732&jsoncallback=";

    private static Retrofit setInit() {
        return new  Retrofit.Builder()
                .baseUrl(Weburl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getInstance() {
        return setInit() .create(ApiService.class);}
}
