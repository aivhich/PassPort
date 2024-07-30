package com.aivhich.passport.data.remote.dto

import com.aivhich.applang.data.remote.dto.request.Role
import com.aivhich.passport.domain.model.User
import java.sql.Time
import java.util.*

data class UserDto(
    var id: UUID,
    val nickname: String,
    var email: String,
    val isEmailVerified: Boolean,
    val lastSeenAt: Time?,
    val nativeLang: String?,
    val interests: List<String?>?,
    val birthday: Date?,
    val role: Role
)

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = "",
        surname = "",
        code = "",
        email = email,
        isEmailVerified = isEmailVerified
    )
}