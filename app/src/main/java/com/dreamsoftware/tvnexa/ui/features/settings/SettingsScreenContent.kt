@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.ui.features.settings.components.SettingsMenu
import com.dreamsoftware.tvnexa.ui.navigation.SettingsNavigation

@Composable
fun SettingsScreenContent(
    navController: NavHostController,
) {
    Row(
        Modifier
            .fillMaxSize(),
    ) {
        SettingsMenu(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.2f))
                .padding(vertical = 32.dp, horizontal = 16.dp),
        ) {
            navController.navigate(it.id)
        }
        SettingsNavigation(navController)
    }
}