package com.aivhich.passport.presentation.states

sealed class AuthStates() {
    data object Login : AuthStates()
    data object SignUp : AuthStates()
    data object StartUp : AuthStates()
    data object EnterCode : AuthStates()
    data object Success : AuthStates()
    data object Fail : AuthStates()
    data object Forget : AuthStates()
}