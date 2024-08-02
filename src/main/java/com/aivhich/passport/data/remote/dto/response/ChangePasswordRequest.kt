package com.aivhich.passport.data.remote.dto.response

data class ChangePasswordRequest(
    private val currentPassword: String? = null,
    private val newPassword: String? = null,
    private val confirmationPassword: String? = null,
)