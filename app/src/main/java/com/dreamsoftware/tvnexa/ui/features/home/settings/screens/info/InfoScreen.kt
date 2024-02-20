@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.settings.screens.info

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.BuildConfig
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonInfoRow
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.extensions.isAndroidTV
import com.dreamsoftware.tvnexa.ui.features.home.settings.components.CommonSettingsContainer
import com.dreamsoftware.tvnexa.utils.getAndroidVersion
import com.dreamsoftware.tvnexa.utils.getCurrentDateTimeFormatted

@Composable
fun InfoScreen() {
    CommonSettingsContainer(titleRes = R.string.info_title) {
        with(MaterialTheme.colorScheme) {
            CommonText(
                titleRes = R.string.info_content_main_title,
                type = CommonTextTypeEnum.BODY_LARGE,
                textBold = true,
                textColor = onSurface
            )
            Spacer(modifier = Modifier.height(35.dp))
            val commonModifier = Modifier.fillMaxWidth()
            CommonInfoRow(
                modifier = commonModifier,
                labelRes = R.string.info_content_platform_label_text,
                value = stringResource(
                    id = if (LocalContext.current.isAndroidTV()) {
                        R.string.info_content_platform_value_tv_text
                    } else {
                        R.string.info_content_platform_value_mobile_text
                    }
                )
            )
            CommonInfoRow(
                modifier = commonModifier,
                labelRes = R.string.info_content_so_version_label_text,
                value = getAndroidVersion()
            )
            CommonInfoRow(
                modifier = commonModifier,
                labelRes = R.string.info_content_version_label_text,
                value = BuildConfig.VERSION_NAME
            )
            CommonInfoRow(
                modifier = commonModifier,
                labelRes = R.string.info_content_compilation_label_text,
                value = BuildConfig.VERSION_CODE.toString()
            )
            CommonInfoRow(
                modifier = commonModifier,
                labelRes = R.string.info_content_current_time_label_text,
                value = getCurrentDateTimeFormatted()
            )
        }
    }
}

@Preview
@Composable
private fun InfoScreenPrev() {
    InfoScreen()
}
