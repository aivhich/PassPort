package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aivhich.passport.R
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.DigitsField
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.SubText
import com.aivhich.zaryaui.SubheadingText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EnterCodeScreen(vm: MainViewModel = hiltViewModel()) {
    val code: String = vm.code.observeAsState().value ?: ""
    val errorState = vm.digitsCodeState.observeAsState()
    val isLoading by vm.doRequest.observeAsState()
    val swipeRefreshState = rememberPullRefreshState(isLoading ?: false, {})
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.email
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1.5f,
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
        }
    ) { pd ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(pd),
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(pd)
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HeadlineText(text = stringResource(id = R.string.check_email))
                Spacer(modifier = Modifier.height(16.dp))
                LottieAnimation(
                    composition = preloaderLottieComposition,
                    progress = preloaderProgress,
                    modifier = Modifier.height(180.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                DigitsField(
                    value = code,
                    length = 6,
                    error = errorState.value == true,
                    onValueChange = {
                        vm.setCode(it)
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                BodyText(
                    text = stringResource(id = R.string.code_entermessage),
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                SubText(text = "Среднее время ожидания письма ~ 3 минуты")
            }
            PullRefreshIndicator(
                vm.doRequest.value ?: false,
                swipeRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}