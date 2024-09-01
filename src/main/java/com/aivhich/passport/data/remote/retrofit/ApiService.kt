package com.aivhich.passport.data.remote.retrofit

import com.aivhich.passport.data.remote.dto.UserDto
import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.passport.data.remote.dto.response.AuthenticationResponse
import com.aivhich.passport.data.remote.dto.response.EmailVerifyResponse
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.passport.data.remote.dto.request.CheckExistRequest
import com.aivhich.passport.data.remote.dto.request.EmailVerifyRequest
import com.aivhich.passport.data.remote.dto.request.StageRequest
import com.aivhich.passport.data.remote.dto.response.CheckExistResponse
import com.aivhich.passport.data.remote.dto.response.StageResponse
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST


interface ApiService {
    @POST("/api/v1/auth/signup")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body register: RegisterRequest): AuthenticationResponse

    @POST("/api/v1/auth/authenticate")
    @Headers("Content-Type: application/json")
    suspend fun authenticate(@Body auth: AuthenticationRequest): AuthenticationResponse

    @POST("/api/v1/auth/userStage")
    @Headers("Content-Type: application/json")
    suspend fun userStage(@Body req: StageRequest): StageResponse

    @PATCH("/api/v1/users/change_password")
    @Headers("Content-Type: application/json")
    suspend fun changePassword(@Body register: AuthenticationRequest): AuthenticationResponse

    @POST("/api/v1/auth/isExistsCheck")
    suspend fun isExist(@Body checkExistRequest: CheckExistRequest): CheckExistResponse

    @GET("/api/v1/users/get")
    @Headers("Content-Type: application/json")
    suspend fun getUser(@Header("Authorization") authorization: String): UserDto?


    @POST("/api/v1/auth/emailVerify")
    @Headers("Content-Type: application/json")
    suspend fun emailVerify(@Body emailVerifyRequest: EmailVerifyRequest): EmailVerifyResponse

    @POST("/api/v1/auth/refresh-token")
    @Headers("Content-Type: application/json")
    suspend fun refreshToken(@Header("Authorization") authorization: String):AuthenticationResponse
}