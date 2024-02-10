package com.dreamsoftware.tvnexa.ui.features.profiles.management

import androidx.compose.runtime.Composable
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent

@Composable
fun ProfilesManagementScreenContent(
    uiState: ProfilesManagementUiState,
    onCompletePressed: () -> Unit
) {
    CommonProfileScreenContent(
        isLoading = uiState.isLoading,
        mainTitleRes = R.string.profiles_management_main_title,
        secondaryTitleRes = R.string.profiles_management_main_description,
        primaryOptionTextRes = R.string.profiles_management_form_confirm_button_text,
        onPrimaryOptionPressed = onCompletePressed
    ) {

    }
}