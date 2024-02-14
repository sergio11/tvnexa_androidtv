@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.settings.screens.help

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.features.home.settings.components.CommonSettingsContainer

@Composable
fun HelpScreen() {
    CommonSettingsContainer(titleRes = R.string.help_title) {
        with(MaterialTheme.colorScheme) {
            CommonText(
                titleRes = R.string.help_content_main_title,
                type = CommonTextTypeEnum.BODY_LARGE,
                textBold = true,
                textColor = onSurface
            )
            Spacer(modifier = Modifier.height(15.dp))
            CommonText(
                titleRes = R.string.help_content_secondary_title,
                type = CommonTextTypeEnum.BODY_SMALL,
                textColor = onSurface
            )
        }
    }
}

@Preview
@Composable
private fun HelpScreenPrev() {
    HelpScreen()
}
