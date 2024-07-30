package com.aivhich.passport.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aivhich.passport.R
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.HyperlinkText
import com.aivhich.zaryaui.LoginField
import com.aivhich.zaryaui.MainButton
import com.aivhich.zaryaui.NicknameField
import com.aivhich.zaryaui.OutlinedField
import com.aivhich.zaryaui.PasswordField
import com.aivhich.zaryaui.SubheadingText

@Composable
fun SignupScreen(vm: MainViewModel, toLogin: () -> Unit) {
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
            HeadlineText(text = stringResource(id = R.string.createacc))
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedField(
                value = "",
                error = "",
                onChange = {},
                label = stringResource(id = R.string.name),
                placeholder = stringResource(id = R.string.name)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedField(
                value = "",
                error = "",
                onChange = {},
                label = stringResource(id = R.string.surname),
                placeholder = stringResource(id = R.string.surname),
            )
            Spacer(modifier = Modifier.height(8.dp))
            LoginField(value = "", error = "", onChange = {})
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(value = "", onChange = {}, submit = {})
            Spacer(modifier = Modifier.height(24.dp))
            MainButton(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .shadow(3.dp, clip = true, shape = RoundedCornerShape(25))
                    .fillMaxWidth(0.7f)
                    .height(42.dp)
            ) {
                BodyText(text = stringResource(id = R.string.signup))
            }
            Spacer(modifier = Modifier.height(12.dp))
            MainButton(
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                onClick = { toLogin() },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(42.dp)
            ) {
                BodyText(text = stringResource(id = R.string.login))
            }
            Spacer(modifier = Modifier.height(48.dp))
            HyperlinkText(
                fullText = "Создавая аккаунт вы соглашаетесь с \n" + "политикой конфеденциальности\nи правилами использования сервиса",
                hyperLinks = mutableMapOf(
                    "политикой конфеденциальности\nи правилами использования сервиса" to "https://applang.ru/terms"
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.W300
                ),
                linkTextColor = Color.Blue,
                fontSize = 14.sp,
            )
            Spacer(Modifier.height(3.dp))
        }
    }
}