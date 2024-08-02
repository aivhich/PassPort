package com.aivhich.passport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aivhich.passport.presentation.screens.AuthScreen
import com.aivhich.passport.presentation.screens.EnterCodeScreen
import com.aivhich.passport.presentation.screens.ForgetScreen
import com.aivhich.passport.presentation.screens.SigninScreen
import com.aivhich.passport.presentation.screens.SignupScreen
import com.aivhich.passport.presentation.screens.StartupScreen
import com.aivhich.passport.ui.theme.PassportTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PassportTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AuthScreen()
                }
            }
        }
    }
}