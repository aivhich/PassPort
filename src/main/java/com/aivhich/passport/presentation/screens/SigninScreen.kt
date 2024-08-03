package com.aivhich.passport.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aivhich.passport.R
import com.aivhich.passport.presentation.vm.MainViewModel
import com.aivhich.zaryaui.BodyText
import com.aivhich.zaryaui.HeadlineText
import com.aivhich.zaryaui.LoginField
import com.aivhich.zaryaui.MainButton
import com.aivhich.zaryaui.PasswordField
import com.aivhich.zaryaui.SubheadingText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SigninScreen(vm: MainViewModel = hiltViewModel(), toSignup: () -> Unit, toForget: () -> Unit) {
    val email = vm.email.observeAsState("")
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
                .padding(pd),
        ) {
            Column(
                Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HeadlineText(text = stringResource(id = R.string.login))
                Spacer(modifier = Modifier.height(24.dp))
                LoginField(value = email.value,
                    onChange = {
                        vm.setEmail(it)
                    })
                Spacer(modifier = Modifier.height(8.dp))
                PasswordField(value = password.value,
                    onChange = { vm.setPassword(it) },
                    submit = { vm.send() })
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
                    BodyText(text = stringResource(id = R.string.login))
                }
                Spacer(modifier = Modifier.height(12.dp))
                MainButton(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    onClick = { toSignup() },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(42.dp)
                ) {
                    BodyText(text = stringResource(id = R.string.signup))
                }
                Spacer(modifier = Modifier.height(12.dp))
                MainButton(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    onClick = { toForget() },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(42.dp)
                ) {
                    BodyText(text = stringResource(id = R.string.forget))
                }
                Spacer(Modifier.height(24.dp))
            }

            PullRefreshIndicator(
                vm.doRequest.value ?: false,
                swipeRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
    LaunchedEffect(key1 = "Toast") {
        vm.message.collect {
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