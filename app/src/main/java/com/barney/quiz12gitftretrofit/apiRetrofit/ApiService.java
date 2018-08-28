package com.barney.quiz12gitftretrofit.apiRetrofit;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    @GET("jakarta.json?key=d1aa7cdf5e8c0d2d7f1f47811497a732")
    Call<com.barney.quiz12gitftretrofit.model.Response> readJadwalSholat();

}
