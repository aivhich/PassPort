package com.aivhich.applang.data.remote.dto.request

import com.google.gson.annotations.SerializedName

enum class Role{
    USER,
}
data class RegisterRequest(
    @SerializedName("username")
    val username:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("role")
    val role: Role
)
