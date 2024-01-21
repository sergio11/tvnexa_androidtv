package com.dreamsoftware.tvnexa.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onGoToOnboarding: () -> Unit,
    onGoToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(6000)
        onGoToOnboarding()
    }
    SplashScreenContent(
        modifier = modifier
    )
}