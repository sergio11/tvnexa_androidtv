@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.profiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.ui.components.CommonFullScreenImage
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ProfileSelector
import com.dreamsoftware.tvnexa.ui.features.profiles.components.ProfileLockedDialog

@Composable
fun ProfileSelectorContent(
    uiState: ProfileSelectorUiState,
    onProfileSelected: (ProfileBO) -> Unit
) {
    with(uiState) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ProfilesBackground()
            ProfileLockedDialog(
                isVisible = isProfileSelectedLocked,
                onDismissPressed = {  },
                onExitPressed = {}
            )
            ProfilesLogo(modifier =
            Modifier
                .align(Alignment.TopStart)
                .padding(20.dp)
                .height(90.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CommonText(
                    titleRes = R.string.profile_selector_main_title,
                    type = CommonTextTypeEnum.HEADLINE_LARGE
                )
                ProfileSelector(
                    profiles = profiles,
                    onProfileSelected = onProfileSelected
                )
                CommonText(
                    titleRes = R.string.profile_selector_secondary_title,
                    type = CommonTextTypeEnum.HEADLINE_MEDIUM
                )
            }
        }
    }
}

@Composable
private fun ProfilesLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.tvnexa_logo_inverse),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
private fun ProfilesBackground() {
    CommonFullScreenImage(resourceId = R.drawable.signup_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    )
}

@Preview(device = Devices.TV_1080p, showBackground = true)
@Composable
private fun ProfilesPreview() {
    ProfileSelectorContent(uiState = ProfileSelectorUiState()) {}
}