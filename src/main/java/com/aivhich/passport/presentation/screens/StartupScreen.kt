package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aivhich.passport.R
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.MainButton
import com.aivhich.zaryaui.SubheadingText

@Composable
fun StartupScreen(viewModel: MainViewModel, toLogin: () -> Unit, toSignup: () -> Unit) {
    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Spacer(modifier = Modifier.height(42.dp))
                SubheadingText(text = "PassPort", textColor = Color.Gray)
            }
        },
        bottomBar = {
            Column(Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                MainButton(
                    onClick = { toSignup() },
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                )
                { BodyText(stringResource(id = R.string.signup)) }
                Spacer(modifier = Modifier.height(12.dp))
                MainButton(
                    onClick = { toLogin() },
                    backgroundColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                ) {
                    BodyText(stringResource(id = R.string.login))
                }
                Spacer(modifier = Modifier.height(42.dp))
            }
        }
    ) { pd ->
        Column(
            Modifier.fillMaxWidth().fillMaxHeight()
                .padding(pd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeadlineText(stringResource(id = R.string.auth_account))
            Spacer(modifier = Modifier.height(42.dp))
            Image(
                painter = painterResource(id = R.drawable.imagelogin),
                "",
                modifier = Modifier.fillMaxWidth(0.7f)
            )
        }
    }
}