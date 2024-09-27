package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.data.datasource.TokenDao
import com.aivhich.passport.data.remote.dto.request.EmailVerifyRequest
import com.aivhich.passport.data.remote.dto.response.EmailVerifyResponse
import com.aivhich.passport.data.remote.retrofit.ApiService
import javax.inject.Inject
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.domain.repository.UserRepository

class VerifyEmailUseCase @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val dao: TokenDao
) {
    suspend operator fun invoke(req: EmailVerifyRequest): Result<EmailVerifyResponse> {
        return try {
            req.accessToken = dao.getToken().accesssToken
            req.email = userDao.get().email
            val answer = apiService.emailVerify(req)
            Result.Success(answer)
        }catch (e:Throwable){
            Result.Error(e)
        }
    }
}