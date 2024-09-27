package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aivhich.passport.R
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.MainButton
import com.aivhich.zaryaui.SubheadingText

@Composable
fun StartupScreen(toLogin: () -> Unit, toSignup: () -> Unit) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.login
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1,
        isPlaying = true,
        speed = 0.7f,
    )
    Scaffold(
        topBar = {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                SubheadingText(text = "PassPort", textColor = Color.Gray)
            }
        },
    ) { pd ->
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(pd)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BodyText(
                stringResource(id = R.string.auth_account),
                fontSize = 28.sp,
                lineHeight = 26.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            LottieAnimation(
                composition = preloaderLottieComposition,
                progress = preloaderProgress,
                modifier = Modifier.height(225.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
            MainButton(
                onClick = { toSignup() },
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .shadow(1.5.dp, clip = true, shape = RoundedCornerShape(25))
                    .fillMaxWidth(0.9f)
                    .height(42.dp)
            )
            { BodyText(stringResource(id = R.string.signup)) }
            Spacer(modifier = Modifier.height(16.dp))
            MainButton(
                onClick = { toLogin() },
                backgroundColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .shadow(1.5.dp, clip = true, shape = RoundedCornerShape(25))
                    .fillMaxWidth(0.9f)
                    .height(42.dp)
            ) {
                BodyText(stringResource(id = R.string.login))
            }
        }
    }
}