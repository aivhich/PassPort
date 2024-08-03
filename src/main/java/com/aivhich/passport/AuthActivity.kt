package com.aivhich.passport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aivhich.passport.presentation.screens.AuthScreen
import com.aivhich.passport.ui.theme.PassportTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PassportTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AuthScreen(toMain = ::toMain)
                }
            }
        }
    }

    fun toMain(access: String?, refresh:String?){
        Log.d("token___", access.toString())
        val s = BuildConfig.MAIN_URL
        val intent = Intent(s)
        intent.putExtra("access_token", access)
        intent.putExtra("refresh_token", refresh)
        startActivity(intent)
    }
}