package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.TokenDao
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.data.remote.dto.response.AuthenticationResponse
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.model.Token
import com.aivhich.passport.domain.model.User
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.domain.repository.UserRepository
import retrofit2.HttpException
import javax.inject.Inject

class UserSignInWithToken @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val userDao: UserDao,
    private val tokenDao: TokenDao,
    private val api: ApiService,
) {
    suspend operator fun invoke(): Result<Token> {
        var token: Token = tokenDao.getToken()
        var user: User? = null
        Log.d("out", token.accesssToken)
        when(val out = userRepository.get(token.accesssToken)){
            is Result.Success->{
                user = out.data
            }
            is Result.Error->{

                Log.d("out", "aaaaaa")
            }
        }
        if (user != null) {
            if (user.isEmailVerified && user.email.isNotEmpty()) {
                return Result.Success(token)
            }
        }


        Log.d("out", "aaaaaa4")
        var answer:AuthenticationResponse? = null;
        try {
            answer = api.refreshToken(token.refreshToken)
        }catch (e:Exception){
            return Result.Error(Exception())
        }
        Log.d("out", "a8aaa")
        token = Token(
            id = 0,
            accesssToken = answer.accessToken,
            refreshToken = answer.refreshToken
        )
        try {
            tokenDao.deleteToken()
        } catch (e: Exception) {
            return Result.Error(e);
        }
        tokenDao.saveToken(token)

        Log.d("out", "sign in with token")
        when(val out = userRepository.get(token.accesssToken)){
            is Result.Success->{
                user = out.data;
            }
            is Result.Error->{}
        }
        try {
            userDao.delete()
        } catch (e: Exception) {
            return Result.Error(e);
        }
        if (user != null) {
            userDao.saveUser(user)
            return Result.Success(token);
        }
        return Result.Error(Exception())
    }
}