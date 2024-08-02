package com.aivhich.passport.data.remote.retrofit

import com.aivhich.passport.BuildConfig


object Common {
    private val BASE_URL = BuildConfig.AUTH_URL//"http://aplang.ru/"
    val retrofitService: ApiService
        get() = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

}