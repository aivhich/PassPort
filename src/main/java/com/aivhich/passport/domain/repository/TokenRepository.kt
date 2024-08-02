package com.aivhich.passport.domain.repository

import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.applang.domain.model.Token
import com.aivhich.passport.common.Result


interface TokenRepository {
    suspend fun login(req: AuthenticationRequest): com.aivhich.passport.common.Result<Token>
    suspend fun signup(req: RegisterRequest): Result<Token>
    fun refresh()
}