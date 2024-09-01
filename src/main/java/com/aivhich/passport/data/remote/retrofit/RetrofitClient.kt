package com.aivhich.passport.data.remote.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null
    private var client: OkHttpClient? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            client = OkHttpClient.Builder()
                .addInterceptor(ApiInterceptor()) // Добавляем наш Interceptor
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                ///.client(client)
                .build()
        }
        return retrofit!!
    }
}