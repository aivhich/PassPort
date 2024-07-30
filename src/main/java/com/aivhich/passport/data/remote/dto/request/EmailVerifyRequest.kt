package com.aivhich.applang.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class EmailVerifyRequest(
    @SerializedName("code")
    val code:Int,
    @SerializedName("email")
    var email:String,
    @SerializedName("accessToken")
    var accessToken:String
)
