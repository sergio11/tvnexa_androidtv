package com.dreamsoftware.tvnexa.ui.features.profiles.selector

import androidx.compose.runtime.Composable
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileSelector
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent

@Composable
fun ProfileSelectorContent(
    uiState: ProfileSelectorUiState,
    onProfileSelected: (ProfileBO) -> Unit,
    onAddProfilePressed: () -> Unit,
    onProfileManagementPressed: () -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = uiState.isLoading,
            error = error,
            mainTitleRes = R.string.profile_selector_main_title,
            secondaryTitleRes = R.string.profile_selector_secondary_title,
            primaryOptionTextRes = R.string.profile_selector_add_profile_button_text,
            secondaryOptionTextRes = R.string.profile_selector_profile_management_button_text,
            onPrimaryOptionPressed = onAddProfilePressed,
            onSecondaryOptionPressed = onProfileManagementPressed,
            onErrorAccepted = onErrorAccepted
        ) {
            CommonProfileSelector(
                profiles = uiState.profiles,
                onProfileSelected = onProfileSelected
            )
        }
    }
}