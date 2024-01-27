package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ChannelLogo(
    modifier: Modifier = Modifier,
    size: Dp,
    logo: String?,
    borderColor: Color? = null
) {
    with(MaterialTheme.colorScheme) {
        CommonAsyncImage(
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .background(onPrimaryContainer)
                .border(2.dp, borderColor ?: onPrimaryContainer, CircleShape),
            context = LocalContext.current,
            contentScale = ContentScale.Fit,
            imageUrl = logo
        )
    }
}