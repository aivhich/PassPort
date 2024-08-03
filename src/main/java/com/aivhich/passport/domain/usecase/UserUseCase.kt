package com.aivhich.passport.domain.usecase


data class UserUseCase(
    val userSignupUseCase: UserSignupUseCase,
    val userVerifyEmailUseCase: VerifyEmailUseCase,
    val isExistUseCase: IsExistUseCase,
    val userStage: UserStageUseCase,
    val userSignInWithToken: UserSignInWithToken
)