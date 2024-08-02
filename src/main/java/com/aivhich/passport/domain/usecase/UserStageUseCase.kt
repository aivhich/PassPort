package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.data.remote.dto.request.StageRequest
import com.aivhich.passport.data.remote.dto.response.StageResponse
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.model.User
import javax.inject.Inject
import com.aivhich.passport.common.Result

class UserStageUseCase @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    suspend operator fun invoke(): Result<StageResponse> {
        val user: User = userDao.get()
        try{
            Log.d("out", "getting stage of user "+user.email)
            val answer = apiService.userStage(StageRequest(user.email))
            return Result.Success(answer)
        }catch (e:Exception){
            return Result.Error(e);
        }
    }
}