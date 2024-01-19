package com.dreamsoftware.tvnexa.ui.features.signin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onGoToHome: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    SignInScreenContent(
        modifier = modifier,
        onLoginClick = { _, _ ->
            onGoToHome()
        },
        onGoToSignUp = onGoToSignUp
    )
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignInScreenPrev() {
    TvNexaTheme {
        SignInScreen(
            modifier = Modifier.fillMaxSize(),
            onGoToHome = {},
            onGoToSignUp = {}
        )
    }
}
