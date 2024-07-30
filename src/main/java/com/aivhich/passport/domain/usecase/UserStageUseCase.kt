package com.aivhich.applang.domain.use_case.user

import android.util.Log
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.applang.data.remote.dto.request.StageRequest
import com.aivhich.applang.data.remote.dto.response.StageResponse
import com.aivhich.applang.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.model.User
import javax.inject.Inject

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