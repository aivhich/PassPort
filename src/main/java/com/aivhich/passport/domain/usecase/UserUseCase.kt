package com.aivhich.passport.domain.usecase

import com.aivhich.passport.domain.usecase.UserSignupUseCase
import com.aivhich.passport.domain.usecase.UserStageUseCase
import com.aivhich.passport.domain.usecase.VerifyEmailUseCase
import com.aivhich.passport.domain.usecase.isExistUseCase

data class UserUseCase(
    val userSignupUseCase: UserSignupUseCase,
    val userVerifyEmailUseCase: VerifyEmailUseCase,
    val isExistUseCase: isExistUseCase,
    val userStage: UserStageUseCase,
)