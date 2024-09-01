package com.aivhich.passport.domain.repository

import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.passport.domain.model.Token
import com.aivhich.passport.common.Result


interface TokenRepository {
    suspend fun login(req: AuthenticationRequest): Result<Token>
    suspend fun signup(req: RegisterRequest): Result<Token>
    fun refresh()
}