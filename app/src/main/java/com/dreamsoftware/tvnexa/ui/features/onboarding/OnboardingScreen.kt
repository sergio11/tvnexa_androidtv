package com.dreamsoftware.tvnexa.ui.features.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit,
) {
    OnboardingScreenContent(
        modifier = modifier,
        onGoToSignIn = onGoToSignIn,
        onGoToSignUp = onGoToSignUp
    )
}