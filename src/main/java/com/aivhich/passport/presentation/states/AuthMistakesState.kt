package com.aivhich.passport.presentation.states

import com.aivhich.passport.common.UiText


data class AuthMistakesState(
    var nicknameError: UiText? = null,
    var surnameError: UiText? = null,
    var nameError: UiText? = null,
    var emailError: UiText? = null,
    var passwordError: UiText? = null
)
