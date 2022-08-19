package com.ravimishra.newstar

import retrofit2.Retrofit
import com.ravimishra.newstar.ApiClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.ravimishra.newstar.Model.News
import retrofit2.Call
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getTopNews(
        @Query("country") country: String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageCount: Int
    ): Call<News>

    @GET("everything")
    fun getNews(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("pageSize") pageCount: Int
    ): Call<News>
}