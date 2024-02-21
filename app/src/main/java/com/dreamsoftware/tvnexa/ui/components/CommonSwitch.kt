@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Switch
import androidx.tv.material3.SwitchDefaults

private const val DEFAULT_MAX_LINES = 5

@Composable
fun CommonSwitch(
    modifier: Modifier = Modifier,
    @StringRes helpTextRes: Int,
    isEnabled: Boolean = true,
    checked: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Switch(
                modifier = Modifier.scale(1.5f),
                checked = checked,
                enabled = isEnabled,
                interactionSource = interactionSource,
                colors = SwitchDefaults.colors(
                    uncheckedBorderColor = if(isFocused) {
                        onPrimary.copy(0.6f)
                    } else {
                        onPrimary
                    },
                    uncheckedThumbColor = onPrimary,
                    uncheckedTrackColor = if(isFocused) {
                        secondary.copy(0.6f)
                    } else {
                        secondary 
                    },
                    checkedBorderColor = if(isFocused) {
                        onPrimary.copy(0.6f)
                    } else {
                        onPrimary
                    },
                    checkedTrackColor = if(isFocused) {
                        primary.copy(0.4f)
                    } else {
                        primary
                    }
                ),
                onCheckedChange = { onValueChanged(it) }
            )
            CommonText(
                modifier = Modifier.fillMaxWidth(0.75f),
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                maxLines = DEFAULT_MAX_LINES,
                titleRes = helpTextRes,
                textColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}