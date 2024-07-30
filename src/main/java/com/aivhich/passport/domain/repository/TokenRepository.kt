package com.aivhich.applang.domain.repository

import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.applang.data.remote.dto.request.RegisterRequest
import com.aivhich.applang.domain.model.Token


interface TokenRepository {
    suspend fun login(req: AuthenticationRequest): Result<Token>
    suspend fun signup(req: RegisterRequest): Result<Token>
    fun refresh()
}