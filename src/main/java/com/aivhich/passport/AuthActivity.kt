package com.aivhich.passport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aivhich.passport.presentation.AuthGraph
import com.aivhich.passport.presentation.screens.EnterCodeScreen
import com.aivhich.passport.presentation.screens.ForgetScreen
import com.aivhich.passport.presentation.screens.SigninScreen
import com.aivhich.passport.presentation.screens.SignupScreen
import com.aivhich.passport.presentation.screens.StartupScreen
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.passport.ui.theme.PassportTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = MainViewModel()
        setContent {
            PassportTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AuthGraph.STARTUP
                    ) {
                        composable(AuthGraph.STARTUP) {
                            StartupScreen(viewModel = viewModel,
                                toLogin = { navController.navigate(AuthGraph.SIGNIN) },
                                toSignup = { navController.navigate(AuthGraph.SIGNUP) })
                        }
                        composable(AuthGraph.SIGNIN) {
                            SigninScreen(toSignup = { navController.navigate(AuthGraph.SIGNUP) },
                                toForget = {navController.navigate(AuthGraph.FORGET)})
                        }
                        composable(AuthGraph.SIGNUP) {
                            SignupScreen(viewModel,
                                toLogin = { navController.navigate(AuthGraph.SIGNIN) })
                        }
                        composable(AuthGraph.FORGET) {
                            ForgetScreen()
                        }
                        composable(AuthGraph.FAIL) {

                        }
                        composable(AuthGraph.SUCCESS) {

                        }
                        composable(AuthGraph.ENTERCODE) {
                            EnterCodeScreen()
                        }
                    }
                }
            }
        }
    }
}