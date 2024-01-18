@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
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
        Row {
            val commonModifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
            OnBoardingLogo(modifier = commonModifier)
            OnboardingContentInfo(modifier = commonModifier)
        }
        OnBoardingActions(
            onGoToSignIn = onGoToSignIn,
            onGoToSignUp = onGoToSignUp
        )
    }
}

@Composable
private fun OnBoardingLogo(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tvnexa_logo_inverse),
            contentDescription = null,
            modifier = Modifier
                .height(190.dp)
                .align(Alignment.TopCenter)
                .padding(top = 60.dp)
        )
    }
}

@Composable
private fun OnboardingContentInfo(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Explore the World of TVNexa!",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Discover a vast collection of television channels and enjoy an immersive viewing experience. Explore international channels with IPTV, bringing you content from around the globe.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Sign in or Sign up to unlock the full potential of TVNexa.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun OnboardingVideoBackground() {
    CommonVideoBackground(videoResourceId = R.raw.onboarding_screen_video_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
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
            text = "Sign Up",
            inverseStyle = true
        )
    }
}