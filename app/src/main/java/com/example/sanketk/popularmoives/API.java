package com.example.sanketk.popularmoives;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("popular")
    Call<ApiResponse> getPopularMovies(@Query("api_key") String str);

    @GET("top_rated")
    Call<ApiResponse> getTopRatedMovies(@Query("api_key") String str);
}
