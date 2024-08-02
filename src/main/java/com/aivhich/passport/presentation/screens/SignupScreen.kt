package com.aivhich.passport.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.passport.R
import com.aivhich.passport.presentation.states.AuthMistakesState
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignupScreen(vm: MainViewModel = hiltViewModel(), toLogin: () -> Unit) {
    val errorState = vm.errors.collectAsState(AuthMistakesState())
    val email = vm.email.observeAsState("")
    val name = vm.name.observeAsState("")
    val surname = vm.surname.observeAsState("")
    val nickname = vm.nickname.observeAsState("")
    val password = vm.password.observeAsState("")
    val mContext = LocalContext.current
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
                .padding(pd)
        ) {
            PullRefreshIndicator(vm.doRequest.value ?: false, swipeRefreshState)
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HeadlineText(text = stringResource(id = R.string.createacc))
                Spacer(modifier = Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth(0.8f)) {
                    OutlinedField(
                        value = name.value,
                        error = errorState.value.nameError?.asString() ?: "",
                        onChange = { vm.setName(it) },
                        label = stringResource(id = R.string.name),
                        placeholder = stringResource(id = R.string.name),
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedField(
                        value = surname.value,
                        error = errorState.value.surnameError?.asString() ?: "",
                        onChange = { vm.setSurname(it) },
                        label = stringResource(id = R.string.surname),
                        placeholder = stringResource(id = R.string.surname),
                        modifier = Modifier.weight(4f)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                NicknameField(
                    value = nickname.value,
                    error = errorState.value.nicknameError?.asString() ?: "",
                    onChange = { vm.setNickname(it) },
                    Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                LoginField(value = email.value,
                    error = errorState.value.emailError?.asString() ?: "",
                    onChange = { vm.setEmail(it) },
                    Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                PasswordField(
                    value = password.value,
                    onChange = { vm.setPassword(it) },
                    error = errorState.value.passwordError?.asString()?:"",
                    submit = { vm.send() },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(24.dp))
                MainButton(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { vm.send() },
                    modifier = Modifier
                        .shadow(3.dp, clip = true, shape = RoundedCornerShape(25))
                        .fillMaxWidth(0.8f)
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
                        .fillMaxWidth(0.8f)
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
    LaunchedEffect(key1 = "Toast") {
        vm.message.collect{
            if (!it.hasBeenHandled) {
                Toast.makeText(
                    mContext,
                    it.getContentIfNotHandled()?.asString(mContext),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}