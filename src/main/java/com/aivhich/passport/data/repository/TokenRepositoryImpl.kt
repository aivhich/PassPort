package com.aivhich.passport.data.repository

import android.util.Log
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.model.Token
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.TokenDao
import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: TokenDao
) : TokenRepository {

    override suspend fun login(req: AuthenticationRequest): Result<Token> {
        return try {
            val answer = api.authenticate(req)
            try {
                dao.deleteToken()
            }catch (e:Exception){ }
            val token = Token(
                id = 0,
                accesssToken = answer.accessToken,
                refreshToken = answer.refreshToken
            )
            dao.saveToken(token)
            Result.Success(token)
        }catch (e:Throwable){
            Result.Error(e)
        }
    }

    override suspend fun signup(req: RegisterRequest): Result<Token> {
        try {
            dao.deleteToken()
        }catch (e:Exception){ }
        val answer = api.register(req)
        val token = Token(id = 0, answer.accessToken, answer.refreshToken)
        dao.saveToken(token)
        return Result.Success(token)

    }
    override fun refresh() {
        TODO("Not yet implemented")
    }
}