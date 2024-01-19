@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground
import com.dreamsoftware.tvnexa.ui.components.ExitAppDialog

@Composable
fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        OnboardingVideoBackground()
        ConfirmExitAppDialog()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                val commonModifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
                OnBoardingLogo(modifier = commonModifier)
                OnboardingContentInfo(modifier = commonModifier)
            }
            OnBoardingActions(
                modifier = Modifier
                    .fillMaxWidth(),
                onGoToSignIn = onGoToSignIn,
                onGoToSignUp = onGoToSignUp
            )
        }
    }
}

@Composable
private fun ConfirmExitAppDialog() {
    var confirmExitApp by remember { mutableStateOf(false) }
    BackHandler { confirmExitApp = true }
    if(confirmExitApp) {
        ExitAppDialog(
            onDismissPressed = {
                confirmExitApp = false
            },
            onExitPressed = {
                confirmExitApp = false
            }
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
            CommonText(
                titleText = "Explore the World of TVNexa!",
                type = CommonTextTypeEnum.HEADLINE_LARGE,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CommonText(
                titleText = "Discover a vast collection of television channels and enjoy an immersive viewing experience. Explore international channels with IPTV, bringing you content from around the globe.",
                type = CommonTextTypeEnum.BODY_LARGE,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            CommonText(
                titleText = "Sign in or Sign up to unlock the full potential of TVNexa.",
                type = CommonTextTypeEnum.BODY_MEDIUM,
                textAlign = TextAlign.Center
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
private fun OnBoardingActions(
    modifier: Modifier,
    onGoToSignIn: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ){
        CommonText(
            titleText = "Build with passion by dreamsoftware. \n Sergio Sánchez Sánchez © 2024",
            type = CommonTextTypeEnum.LABEL_MEDIUM,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        CommonButton(
            type = CommonButtonTypeEnum.LARGE,
            text = "Sign In",
            onClick = onGoToSignIn,
        )
        Spacer(modifier = Modifier.width(30.dp))
        CommonButton(
            type = CommonButtonTypeEnum.LARGE,
            onClick = onGoToSignUp,
            text = "Sign Up",
            inverseStyle = true
        )
    }
}