package com.aivhich.passport.data.remote.dto

import com.aivhich.passport.data.remote.dto.request.Role
import com.aivhich.passport.domain.model.User
import com.google.gson.annotations.SerializedName
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

data class UserDto(
    @SerializedName("id")
    var id: UUID,
    @SerializedName("name")
    val name:String,
    @SerializedName("surname")
    val surname:String,
    @SerializedName("code")
    val code:String?,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("emailVerified")
    val isEmailVerified: Boolean,
    @SerializedName("role")
    val role: Role
)
////error here sync classes
fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        surname = surname,
        code = code,
        email = email,
        isEmailVerified = isEmailVerified,
        timestamp = ZonedDateTime.now(ZoneId.of("UTC-0")).toEpochSecond()
    )
}