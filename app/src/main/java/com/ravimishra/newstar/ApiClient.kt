package com.ravimishra.newstar

import retrofit2.Retrofit
import com.ravimishra.newstar.ApiClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.ravimishra.newstar.Model.News

object ApiClient {
    const val BASE_URL = "https://newsapi.org/v2/"
    private var retrofit: Retrofit? = null
    @JvmStatic
    val apiClient: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}