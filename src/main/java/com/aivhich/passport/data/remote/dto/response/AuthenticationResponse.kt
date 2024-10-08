package com.aivhich.passport.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(

    @SerializedName("access_token")
    val accessToken:String,

    @SerializedName("refresh_token")
    val refreshToken:String,
    @SerializedName("roles")
    val roles:String
)