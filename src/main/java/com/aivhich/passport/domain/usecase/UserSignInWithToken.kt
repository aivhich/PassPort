package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.TokenDao
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.domain.model.Token
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.domain.repository.UserRepository
import javax.inject.Inject

class UserSignInWithToken @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val userDao: UserDao,
    private val tokenDao: TokenDao
) {
    suspend operator fun invoke(): Result<Token> {
        val token: Token = tokenDao.getToken()
        val user = userRepository.get(token.accesssToken)
        Log.d("out", user.isEmailVerified.toString())
        Log.d("out", user.email.isNotEmpty().toString())
        if(user.isEmailVerified && user.email.isNotEmpty()){
            return Result.Success(token)
        }else{
            //refresh here
        }
        return Result.Error(Exception());
    }
}