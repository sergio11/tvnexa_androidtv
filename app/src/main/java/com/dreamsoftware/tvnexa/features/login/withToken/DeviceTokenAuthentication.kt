package com.dreamsoftware.tvnexa.features.login.withToken

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.tvnexa.navigation.Screens
import com.dreamsoftware.tvnexa.theme.ComposeTvTheme

@Composable
fun DeviceTokenAuthenticationScreen(
    modifier: Modifier = Modifier,
    onSkip: () -> Unit,
    goToScreen: (Screens) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DeviceTokenAuthenticationContent(token = "OTF2", "www.google.com", skip = onSkip) {
            goToScreen(Screens.Login)
        }
    }
}


@Preview
@Composable
private fun DeviceTokenAuthenticationScreenPreview() {
    ComposeTvTheme {
        DeviceTokenAuthenticationScreen(Modifier.fillMaxSize(), onSkip = {}) {

        }
    }
}