package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.passport.domain.model.Token
import com.aivhich.passport.domain.repository.TokenRepository
import javax.inject.Inject
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.domain.model.User
import com.aivhich.passport.domain.repository.UserRepository

class UserSignupUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val userDao: UserDao
) {
    suspend operator fun invoke(req: RegisterRequest): Result<Token> {
        val answer = tokenRepository.signup(req)
        when(answer){
            is Result.Error -> {
                return Result.Error(answer.throwable)
            }
            is Result.Success -> {
                val token: Token = answer.data

                Log.d("out", "sign up")
                var user: User? = null;
                when(val out = userRepository.get(token.accesssToken)){
                    is Result.Error->{}
                    is Result.Success->{
                        user = out.data
                    }
                }

                userDao.delete()
                if (user != null) {
                    userDao.saveUser(user)
                    return Result.Success(answer.data)
                }
                return Result.Error(Exception())
            }
        }
    }
}