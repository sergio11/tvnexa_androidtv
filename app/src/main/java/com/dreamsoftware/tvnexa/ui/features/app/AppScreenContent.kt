package com.dreamsoftware.tvnexa.ui.features.app

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.components.CommonScreenContent
import com.dreamsoftware.tvnexa.ui.components.LostNetworkConnectivityDialog
import com.dreamsoftware.tvnexa.ui.navigation.MainNavigation

@Composable
fun AppScreenContent(
    uiState: AppUiState,
    navController: NavHostController,
    onOpenSettingsPressed: () -> Unit,
    onRestartAppPressed: () -> Unit
) {
    with(uiState) {
        CommonScreenContent(
            error = error
        ) {
            Box {
                MainNavigation(navController)
                LostNetworkConnectivityDialog(
                    isVisible = !hasNetworkConnectivity,
                    onOpenSettings = onOpenSettingsPressed,
                    onRestartAppPressed = onRestartAppPressed
                )
            }
        }
    }
}