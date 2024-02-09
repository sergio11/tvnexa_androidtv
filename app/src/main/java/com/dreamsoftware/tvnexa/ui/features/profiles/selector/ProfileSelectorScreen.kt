package com.dreamsoftware.tvnexa.ui.features.profiles.selector

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun ProfileSelectorScreen(
    viewModel: ProfileSelectorViewModel = hiltViewModel(),
    onProfileSelected: () -> Unit,
    onProfileLocked: (String) -> Unit,
    onGoToAddProfile: () -> Unit,
    onGoToProfileManagement: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = {  loadProfiles() },
        onInitialUiState = { ProfileSelectorUiState() },
        onSideEffect = {
            when(it) {
                is ProfileSelectorSideEffects.ProfileLocked -> onProfileLocked(it.profileId)
                ProfileSelectorSideEffects.ProfileSelected -> onProfileSelected()
            }
        }
    ) { uiState ->
        ProfileSelectorContent(
            uiState = uiState,
            onProfileSelected = ::onProfileSelected,
            onAddProfilePressed = onGoToAddProfile,
            onProfileManagementPressed = onGoToProfileManagement
        )
    }
}
