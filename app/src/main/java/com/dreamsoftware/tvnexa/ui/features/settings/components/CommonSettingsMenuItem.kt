@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.dreamsoftware.tvnexa.ui.components.FocusableItem
import com.dreamsoftware.tvnexa.ui.features.settings.model.SettingsItemMenu

@Composable
fun CommonSettingsMenuItem(item: SettingsItemMenu, onMenuSelected: (SettingsItemMenu) -> Unit) {
    FocusableItem(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onMenuSelected(item) }) {
        Text(
            text = item.title, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}
