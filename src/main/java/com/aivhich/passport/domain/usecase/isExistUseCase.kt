package com.aivhich.applang.domain.use_case.user

import android.util.Log
import com.aivhich.applang.data.Result
import com.aivhich.applang.data.remote.dto.request.CheckExistRequest
import com.aivhich.applang.data.remote.dto.response.CheckExistResponse
import com.aivhich.applang.data.remote.retrofit.ApiService
import javax.inject.Inject

class isExistUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(checkExistRequest: CheckExistRequest): Result<CheckExistResponse> {

        Log.d("out", "is exist check")
        return Result.Success(apiService.isExist(checkExistRequest))
    }
}