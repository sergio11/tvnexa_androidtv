@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

@Composable
fun CommonGradientBox(content: @Composable BoxScope.() -> Unit) {
    with(MaterialTheme.colorScheme) {
        val gradient = Brush.linearGradient(
            colors = listOf(
                onPrimary,
                primary.copy(alpha = 0.5f)
            ),
            tileMode = TileMode.Clamp
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradient),
            content = content
        )
    }
}