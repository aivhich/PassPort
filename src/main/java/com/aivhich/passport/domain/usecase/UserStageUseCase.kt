package com.aivhich.passport.domain.usecase

import com.aivhich.passport.common.Result
import com.aivhich.passport.data.remote.dto.request.StageRequest
import com.aivhich.passport.data.remote.dto.response.StageResponse
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.repository.UserRepository
import javax.inject.Inject

class UserStageUseCase @Inject constructor(
    private val apiService: ApiService,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token:String): Result<StageResponse> {

        try {
            when (val user = userRepository.get(token)) {
                is Result.Success -> {
                    val answer = apiService.userStage(StageRequest(user.data.email))
                    return Result.Success(answer)
                }
                is Result.Error->{
                    return Result.Error(Exception());
                }
            }
        }catch (e:Exception){
            return Result.Error(e);
        }
    }
}