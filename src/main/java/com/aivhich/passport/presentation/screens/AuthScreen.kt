package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.passport.presentation.states.AuthStates
import com.aivhich.passport.presentation.vm.MainViewModel

@Composable
fun AuthScreen(vm:MainViewModel = hiltViewModel()){
    val state = vm.state.observeAsState().value
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when(state){
            is AuthStates.Login -> {
                SigninScreen(vm = vm, toSignup = {}, toForget = {})
            }
            is AuthStates.StartUp -> {
                StartupScreen(toLogin = {}) {

                }
            }
            is AuthStates.SignUp -> {
                SignupScreen(vm = vm, toLogin = {})
            }
            is AuthStates.EnterCode -> {
                EnterCodeScreen()
            }
            is AuthStates.Forget -> {

            }
            is AuthStates.Fail -> {

            }
            is AuthStates.Success -> {

            }
            null -> TODO()
        }
    }
}