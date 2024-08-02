package com.aivhich.passport.data.remote.dto.request

import com.google.gson.annotations.SerializedName

enum class Role{
    USER,
}
data class RegisterRequest(
    @SerializedName("name")
    val name:String,
    @SerializedName("surname")
    val surname:String,
    @SerializedName("username")
    val username:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("role")
    val role: Role
)
