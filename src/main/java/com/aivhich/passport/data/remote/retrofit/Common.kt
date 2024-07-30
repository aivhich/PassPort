package com.aivhich.passport.data.remote.retrofit

import com.aivhich.applang.data.remote.retrofit.RetrofitClient


object Common {
    private val BASE_URL = "http://192.168.96.4:8080/"//"http://aplang.ru/"
    val retrofitService: ApiService
        get() = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
}