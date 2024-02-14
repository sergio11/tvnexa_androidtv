@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonFocusableItem
import com.dreamsoftware.tvnexa.ui.features.home.settings.model.SettingsItemMenu

@Composable
fun CommonSettingsMenuItem(item: SettingsItemMenu, onMenuSelected: (SettingsItemMenu) -> Unit) {
    CommonFocusableItem(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onMenuSelected(item) }
    ) { isFocused ->
        CommonText(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            type = CommonTextTypeEnum.BODY_MEDIUM,
            titleRes = item.title,
            textColor = with(MaterialTheme.colorScheme) {
                if(isFocused) {
                    primary
                } else {
                    onPrimary
                }
            }
        )
    }
}
