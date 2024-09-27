package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aivhich.passport.R
import com.aivhich.passport.presentation.states.AuthMistakesState
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.zaryaui.DigitsField
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.LoginField
import com.aivhich.zaryaui.OutlinedField
import com.aivhich.zaryaui.SubheadingText

@Composable
fun ForgetScreen(vm: MainViewModel) {
    val email = vm.email.observeAsState().value ?: ""
    val code: String = vm.code.observeAsState().value ?: ""

    val errorState = vm.errors.collectAsState(AuthMistakesState())
    val errorCodeState = vm.digitsCodeState.observeAsState()
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(pd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeadlineText(text = stringResource(id = R.string.reget_account))
            Spacer(modifier = Modifier.height(24.dp))
            LoginField(
                value = email,
                error = errorState.value.emailError?.asString() ?: "",
                onChange = {
                    vm.setEmail(it)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            DigitsField(
                value = code,
                length = 6,
                error = errorCodeState.value == true,
                onValueChange = {
                    vm.setCode(it)
                }
            )
        }
    }
}