package com.dreamsoftware.tvnexa.ui.features.profiles

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen

@Composable
fun ProfileSelectorScreen(
    viewModel: ProfileSelectorViewModel = hiltViewModel(),
    onProfileSelected: () -> Unit
) {
    CommonScreen(
        viewModel = viewModel,
        onInit = {  loadProfiles() },
        onInitialUiState = { ProfileSelectorUiState() },
        onSideEffect = {
            if(it is ProfileSelectorSideEffects.ProfileSelected) {
                onProfileSelected()
            }
        }
    ) { uiState ->
        ProfileSelectorContent(
            uiState = uiState,
            onProfileSelected = ::onProfileSelected
        )
    }
}
