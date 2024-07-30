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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aivhich.passport.R
import com.aivhich.zaryaui.DigitsField
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.LoginField
import com.aivhich.zaryaui.OutlinedField
import com.aivhich.zaryaui.SubheadingText

@Composable
fun ForgetScreen() {
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(pd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HeadlineText(text = stringResource(id = R.string.reget_account))
            Spacer(modifier = Modifier.height(24.dp))
            LoginField(value = "", error = "", onChange = {})
            Spacer(modifier = Modifier.height(24.dp))
            DigitsField(value = "", length = 6, error = false) {
            }
        }
    }
}