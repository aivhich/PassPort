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
import androidx.compose.material.Icon
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.passport.R
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.DigitsField
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.SubheadingText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EnterCodeScreen(vm: MainViewModel = hiltViewModel()) {
    val code: String = vm.code.observeAsState().value ?: ""
    val errorState = vm.digitsCodeState.observeAsState()
    val isLoading by vm.doRequest.observeAsState()
    val swipeRefreshState = rememberPullRefreshState(isLoading ?: false, {})

    Scaffold(
        topBar = {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(42.dp))
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
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                HeadlineText(text = stringResource(id = R.string.check_email))
                Spacer(modifier = Modifier.height(24.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_mark_email_unread_24),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(12.dp))
                BodyText(
                    text = stringResource(id = R.string.code_entermessage),
                    modifier = Modifier.fillMaxWidth(0.8f)
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
            }
            PullRefreshIndicator(
                vm.doRequest.value ?: false,
                swipeRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}