package com.aivhich.applang.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

object RetrofitClient2 {
    private var retrofit2: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit2 == null) {
            retrofit2 = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit2!!
    }
}

object RetrofitChatClient {
    private var retrofitChat: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit {
        if (retrofitChat == null) {
            retrofitChat = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitChat!!
    }
}