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
    goToHomeScreen: () -> Unit,
) {
    SignInScreenContent(modifier = modifier) { _, _ ->
        goToHomeScreen()
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun LoginScreenPrev() {
    TvNexaTheme {
        SignInScreen(Modifier.fillMaxSize()) {
        }
    }
}
