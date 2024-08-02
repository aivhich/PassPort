package com.aivhich.passport.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class StageRequest(
    @SerializedName("email")
    val email:String
)