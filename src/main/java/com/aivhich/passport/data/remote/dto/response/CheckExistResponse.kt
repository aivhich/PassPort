package com.aivhich.applang.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class CheckExistResponse(
    @SerializedName("nickname")
    val nickname:Boolean,
    @SerializedName("email")
    val email:Boolean
)
