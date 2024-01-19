package com.dreamsoftware.tvnexa.ui.features.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onGoToHomeScreen: () -> Unit,
    onBack: () -> Unit
) {
    SignUpScreenContent(
        modifier = modifier,
        onSigUpButtonPressed = onGoToHomeScreen,
        onCancelPressed = onBack
    )
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignUpScreenPrev() {
    TvNexaTheme {
        SignUpScreen(
            modifier = Modifier.fillMaxSize(),
            onGoToHomeScreen = {},
            onBack = {}
        )
    }
}
