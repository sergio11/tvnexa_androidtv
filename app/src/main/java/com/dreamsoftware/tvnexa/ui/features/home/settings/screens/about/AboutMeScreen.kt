@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.settings.screens.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.features.home.settings.components.CommonSettingsContainer

@Composable
fun AboutScreen() {
    CommonSettingsContainer(title = "About") {
        CommonText(
            titleRes = R.string.about_developer,
            type = CommonTextTypeEnum.BODY_MEDIUM,
            textColor = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun AboutScreenPrev() {
    AboutScreen()
}
