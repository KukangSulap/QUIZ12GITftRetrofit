package com.barney.quiz12gitftretrofit.apiRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceRetrofit {
    private static final String Weburl = "http://muslimsalat.com/london/";

    private static Retrofit setInit() {
        return new  Retrofit.Builder()
                .baseUrl(Weburl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getInstance() {
        return setInit() .create(ApiService.class);}
}
