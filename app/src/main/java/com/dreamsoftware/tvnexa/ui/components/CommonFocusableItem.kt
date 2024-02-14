@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceBorder
import androidx.tv.material3.ClickableSurfaceColors
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceGlow
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.ClickableSurfaceShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme.colorScheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@Composable
fun CommonFocusableItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: ClickableSurfaceShape = ClickableSurfaceDefaults.shape(
        shape = ShapeDefaults.Small,
        focusedShape = ShapeDefaults.Small
    ),
    color: ClickableSurfaceColors = ClickableSurfaceDefaults.colors(
        containerColor = colorScheme.primary,
        focusedContainerColor = colorScheme.onPrimary,
        contentColor = colorScheme.onPrimary,
        focusedContentColor = colorScheme.primary
    ),
    glow: ClickableSurfaceGlow = ClickableSurfaceDefaults.glow(
        focusedGlow = Glow(
            elevation = 5.dp,
            elevationColor = colorScheme.onPrimary.copy(alpha = 0.3f)
        )
    ),
    border: ClickableSurfaceBorder = ClickableSurfaceDefaults.border(
        border = Border(border = BorderStroke(width = 2.dp, color = colorScheme.onPrimary)),
        focusedBorder = Border(border = BorderStroke(width = 2.dp, color = colorScheme.primary))
    ),
    scale: ClickableSurfaceScale = ClickableSurfaceDefaults.scale(focusedScale = 1.1f),
    content: @Composable (BoxScope.(isFocused: Boolean) -> Unit)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    Surface(
        onClick = { onClick() },
        scale = scale,
        colors = color,
        glow = glow,
        shape = shape,
        border = border,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        content(isFocused)
    }
}

@Preview
@Composable
fun FocusableItemPrev() {
    CommonFocusableItem(onClick = {}) {
        Text(text = "Preview Text")
    }
}
