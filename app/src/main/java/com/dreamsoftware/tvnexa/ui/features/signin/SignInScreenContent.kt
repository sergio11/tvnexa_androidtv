@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, password: String) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoginVideoBackground()
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val commonModifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp)
            LoginFormInfo(modifier = commonModifier)
            LoginFormContent(modifier = commonModifier, onLoginClick = onLoginClick)
        }
    }
}

@Composable
private fun LoginVideoBackground() {
    CommonVideoBackground(videoResourceId = R.raw.login_screen_video_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
    )
}

@Composable
private fun LoginFormInfo(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tvnexa_logo),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .align(Alignment.TopStart)
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Welcome to TVNexa!",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Enter your credentials to explore a diverse universe of television channels from around the world.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun LoginFormContent(
    modifier: Modifier,
    onLoginClick: (email: String, password: String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        LoginScreenHeading("Access Your Account")
        Spacer(modifier = Modifier.height(20.dp))
        TvTextField(value = username.value, label = "Username") {
            username.value = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        TvTextField(
            value = password.value,
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
        ) { password.value = it }
        Spacer(modifier = Modifier.height(40.dp))
        CommonButton(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            onClick = { onLoginClick(username.value, password.value) },
            text = "Sign In"
        )
    }
}

@Composable
private fun LoginScreenHeading(heading: String) {
    Text(
        text = heading,
        style = MaterialTheme.typography.headlineLarge,
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun LoginPagePrev() {
    TvNexaTheme {
        SignInScreenContent { u, p ->
        }
    }
}
