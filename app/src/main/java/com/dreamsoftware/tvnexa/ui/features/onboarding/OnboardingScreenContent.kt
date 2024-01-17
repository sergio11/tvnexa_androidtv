@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground

@Composable
fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        OnboardingVideoBackground()
        OnBoardingActions(
            onGoToSignIn = onGoToSignIn,
            onGoToSignUp = onGoToSignUp
        )
    }
}

@Composable
private fun OnboardingVideoBackground() {
    CommonVideoBackground(videoResourceId = R.raw.login_screen_video_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
    )
}

@Composable
private fun BoxScope.OnBoardingActions(
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    Row (
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(30.dp)
    ){
        CommonButton(
            onClick = onGoToSignIn,
            text = "Sign In"
        )
        Spacer(modifier = Modifier.width(30.dp))
        CommonButton(
            onClick = onGoToSignUp,
            text = "Sign Up"
        )
    }
}