package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aivhich.passport.R
import com.aivhich.zaryaui.DigitsField
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.SubText
import com.aivhich.zaryaui.SubheadingText

@Composable
fun EnterCodeScreen() {
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
        Column(Modifier.padding(pd)){
            Image(painter = painterResource(id = R.drawable.baseline_mark_email_unread_24),
                contentDescription = "")
            Spacer(modifier = Modifier.height(24.dp))
            HeadlineText(text = stringResource(id = R.string.check_email))
            Spacer(modifier = Modifier.height(12.dp))
            SubText(text = stringResource(id = R.string.emailcodeabout))
            Spacer(modifier = Modifier.height(12.dp))
            DigitsField(value = "", length = 6, error = false, onValueChange = {})
        }
    }
}