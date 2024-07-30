package com.aivhich.applang.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class EmailVerifyResponse(

    @SerializedName("isValid")
    val isValid:Boolean,

    @SerializedName("message")
    val message:String,
)
