package com.dreamsoftware.tvnexa.ui.features.home.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.dreamsoftware.tvnexa.ui.features.home.settings.components.SettingsMenu
import com.dreamsoftware.tvnexa.ui.navigation.SettingsNavigation

@Composable
fun SettingsScreenContent(
    navController: NavHostController,
) {
    Row(
        Modifier
            .fillMaxSize(),
    ) {
        SettingsMenu {
            navController.navigate(it.id)
        }
        SettingsNavigation(navController)
    }
}