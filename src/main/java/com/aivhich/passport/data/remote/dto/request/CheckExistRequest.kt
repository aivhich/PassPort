package com.aivhich.applang.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class CheckExistRequest(
    @SerializedName("nickname")
    val nickname:String,
    @SerializedName("email")
    val email:String
)
