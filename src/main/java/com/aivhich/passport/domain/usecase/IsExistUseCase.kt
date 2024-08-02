package com.aivhich.passport.domain.usecase

import android.util.Log
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.remote.dto.request.CheckExistRequest
import com.aivhich.passport.data.remote.dto.response.CheckExistResponse
import com.aivhich.passport.data.remote.retrofit.ApiService
import javax.inject.Inject

class IsExistUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(checkExistRequest: CheckExistRequest): Result<CheckExistResponse> {

        Log.d("out", "is exist check")
        return Result.Success(apiService.isExist(checkExistRequest))
    }
}