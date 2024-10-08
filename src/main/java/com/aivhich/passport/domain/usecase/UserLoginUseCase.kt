package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.passport.domain.model.Token
import com.aivhich.passport.domain.model.User
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.domain.repository.UserRepository
import javax.inject.Inject

class UserLoginUseCase@Inject constructor(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(req: AuthenticationRequest): Result<Token> {
        val answer = tokenRepository.login(req)
        when(answer){
            is Result.Error -> {
                return Result.Error(answer.throwable)
            }
            is Result.Success -> {
                val token: Token = answer.data
                val user: Result<User> = userRepository.get(token.accesssToken)
                when(user){
                    is Result.Success->{
                        userRepository.delete()
                        userRepository.save(user.data)
                        return Result.Success(answer.data)
                    }
                    is Result.Error->{

                    }
                }
            }
        }
        return Result.Error(Exception())
    }
}
