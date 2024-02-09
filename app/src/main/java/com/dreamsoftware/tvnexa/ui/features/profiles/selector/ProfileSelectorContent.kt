package com.dreamsoftware.tvnexa.ui.features.profiles.selector

import androidx.compose.runtime.Composable
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.ui.components.ProfileSelector
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent

@Composable
fun ProfileSelectorContent(
    uiState: ProfileSelectorUiState,
    onProfileSelected: (ProfileBO) -> Unit,
    onAddProfilePressed: () -> Unit,
    onProfileManagementPressed: () -> Unit
) {
    CommonProfileScreenContent(
        uiState = uiState,
        mainTitleRes = R.string.profile_selector_main_title,
        secondaryTitleRes = R.string.profile_selector_secondary_title,
        acceptRes = R.string.profile_selector_add_profile_button_text,
        cancelRes = R.string.profile_selector_profile_management_button_text,
        onAcceptPressed = onAddProfilePressed,
        onCancelPressed = onProfileManagementPressed
    ) {
        ProfileSelector(
            profiles = uiState.profiles,
            onProfileSelected = onProfileSelected
        )
    }
}