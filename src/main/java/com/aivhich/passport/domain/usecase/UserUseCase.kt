package com.aivhich.applang.domain.use_case.user

data class UserUseCase(
    val userSignupUseCase: UserSignupUseCase,
    val userVerifyEmailUseCase: VerifyEmailUseCase,
    val isExistUseCase: isExistUseCase,
    val userStage:UserStageUseCase,
)