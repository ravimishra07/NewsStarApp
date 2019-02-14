package com.ravimishra.newstar;

import com.ravimishra.newstar.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<News> getTopNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<News> getNews(
            @Query("q") String q,

            @Query("apiKey") String apiKey
    );
}
