package com.aivhich.applang.data.repository

import android.util.Log
import com.aivhich.passport.data.datasource.TokenDao
import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.applang.data.remote.dto.request.RegisterRequest
import com.aivhich.applang.data.remote.retrofit.ApiService
import com.aivhich.applang.domain.model.Token
import com.aivhich.applang.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: TokenDao
) : TokenRepository {

    override suspend fun login(req: AuthenticationRequest):Result<Token> {
        return try {
            val answer = api.authenticate(req)
            dao.deleteToken()
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
        }catch (e:Exception){
            Log.d("out", "delete previous token error")
        }
        val answer = api.register(req)
        Log.d("out", answer.accessToken)
        val token = Token(id = 0, answer.accessToken, answer.refreshToken)
        dao.saveToken(token)
        Log.d("out", "signup result ")
        return Result.Success(token)

    }
    override fun refresh() {
        TODO("Not yet implemented")
    }
}