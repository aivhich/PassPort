package com.aivhich.passport.data.remote.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())

        if (response.code() === 403) {
            Log.e("out", "403 error")
        }

        return response
    }
}