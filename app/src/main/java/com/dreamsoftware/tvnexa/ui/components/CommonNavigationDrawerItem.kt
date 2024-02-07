@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerScope

@Composable
fun NavigationDrawerScope.CommonNavigationDrawerItem(
    isSelected: Boolean = false,
    enabled: Boolean = true,
    onPressed: () -> Unit = {},
    leadingContent: @Composable (isFocused: Boolean) -> Unit,
    content: @Composable (isFocused: Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    with(MaterialTheme.colorScheme) {
        NavigationDrawerItem(
            selected = isSelected,
            enabled = enabled,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = primary.copy(alpha = 0.5f),
                focusedContainerColor = primary,
            ),
            onClick = onPressed,
            leadingContent = {
                leadingContent(isFocused)
            },
            interactionSource = interactionSource
        ) {
            content(isFocused)
        }
    }
}