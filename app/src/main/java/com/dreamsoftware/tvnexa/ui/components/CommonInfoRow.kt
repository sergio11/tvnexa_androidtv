@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

@Composable
fun CommonInfoRow(
    modifier: Modifier = Modifier,
    @StringRes labelRes: Int,
    value: String
) {
    with(MaterialTheme.colorScheme) {
        Row(
            modifier = modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonText(
                titleRes = labelRes,
                type = CommonTextTypeEnum.BODY_SMALL,
                textColor = onSurface
            )
            Spacer(modifier = Modifier.width(5.dp))
            CommonText(
                titleText = value,
                type = CommonTextTypeEnum.BODY_SMALL,
                textBold = true,
                textColor = onSurface
            )
        }
    }
}