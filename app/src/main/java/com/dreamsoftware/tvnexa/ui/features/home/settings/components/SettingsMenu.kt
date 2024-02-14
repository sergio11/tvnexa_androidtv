@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.ui.features.home.settings.data.SettingsMenuData
import com.dreamsoftware.tvnexa.ui.features.home.settings.model.SettingsItemMenu

@Composable
fun SettingsMenu(
    modifier: Modifier = Modifier,
    onMenuSelected: (SettingsItemMenu) -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val settingsMenu = remember { SettingsMenuData.menu }
        TvLazyColumn(modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.2f)
            .background(primaryContainer.copy(alpha = 0.5f))
            .border(1.dp, primary)
            .padding(top = 50.dp)) {
            items(settingsMenu.size) {
                val item = settingsMenu[it]
                CommonSettingsMenuItem(item) {
                    onMenuSelected(item)
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsMenuPrev() {
    SettingsMenu {}
}
