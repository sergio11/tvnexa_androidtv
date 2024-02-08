@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.profiles.selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.ui.components.AppLogoInverse
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonFullScreenImage
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.LoadingDialog
import com.dreamsoftware.tvnexa.ui.components.ProfileSelector
import com.dreamsoftware.tvnexa.ui.features.profiles.components.ProfileLockedDialog

@Composable
fun ProfileSelectorContent(
    uiState: ProfileSelectorUiState,
    onProfileSelected: (ProfileBO) -> Unit,
    onVerifyPin: (ProfileBO, String) -> Unit,
    onProfileSelectionCancelled: () -> Unit,
    onAddProfilePressed: () -> Unit,
    onProfileManagementPressed: () -> Unit
) {
    with(uiState) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ProfilesBackground()
            LoadingDialog(
                isShowingDialog = isLoading,
                titleRes = R.string.generic_progress_dialog_title,
                descriptionRes = R.string.generic_progress_dialog_description
            )
            profileSelected?.let { profile ->
                ProfileLockedDialog(
                    isVisible = showUnlockProfileDialog,
                    onDismissPressed = onProfileSelectionCancelled,
                    onUnlockPressed = { unlockPin ->
                        onVerifyPin(profile, unlockPin)
                    }
                )
            }
            AppLogoInverse(modifier = Modifier
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
                ProfilesActions(
                    onAddProfilePressed = onAddProfilePressed,
                    onProfileManagementPressed = onProfileManagementPressed
                )
            }
        }
    }
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

@Composable
private fun ProfilesActions(
    modifier: Modifier = Modifier,
    onAddProfilePressed: () -> Unit,
    onProfileManagementPressed: () -> Unit
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        CommonButton(
            textRes = R.string.profile_selector_add_profile_button_text,
            onClick = onAddProfilePressed,
        )
        Spacer(modifier = Modifier.width(30.dp))
        CommonButton(
            textRes = R.string.profile_selector_profile_management_button_text,
            onClick = onProfileManagementPressed,
            style = CommonButtonStyleTypeEnum.INVERSE
        )
    }
}