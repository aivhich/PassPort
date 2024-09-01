package com.aivhich.passport.presentation.screens

import android.util.Log
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
fun AuthScreen(vm: MainViewModel = hiltViewModel(), toMain:(a:String, r:String)->Unit) {
    val state = vm.state.observeAsState().value
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        when (state) {
            is AuthStates.Loading -> {
                LoadScreen()
            }
            is AuthStates.Login -> {
                SigninScreen(
                    vm = vm,
                    toSignup = {
                        vm.setState(AuthStates.SignUp)
                    },
                    toForget = {
                        vm.setState(AuthStates.Forget)
                    }
                )
            }

            is AuthStates.StartUp -> {
                StartupScreen(
                    toLogin = {
                        vm.setState(AuthStates.Login)
                    },
                    toSignup = {
                        vm.setState(AuthStates.SignUp)
                    }
                )
            }

            is AuthStates.SignUp -> {
                SignupScreen(
                    vm = vm,
                    toLogin = {
                        vm.setState(AuthStates.Login)
                    }
                )
            }

            is AuthStates.EnterCode -> {
                EnterCodeScreen(
                    vm = vm
                )
            }

            is AuthStates.Forget -> {
                ForgetScreen(
                    vm = vm
                )
            }

            is AuthStates.Fail -> {
                FailScreen()
            }

            is AuthStates.Success -> {
                Log.d("token_", state.a)
                toMain(state.a, state.r)
            }

            else -> {}
        }
    }
}