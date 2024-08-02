package com.aivhich.passport.domain.usecase

import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.applang.domain.model.Token
import com.aivhich.passport.domain.model.User
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.domain.repository.UserRepository
import javax.inject.Inject
import com.aivhich.passport.common.Result

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
                val token:Token = answer.data
                val user: User = userRepository.get(token.accesssToken)
                userDao.delete()
                userDao.saveUser(user)
                return Result.Success(answer.data)
            }
        }

    }
}