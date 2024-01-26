package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ScalableAvatar(
    modifier: Modifier,
    @DrawableRes avatarRes: Int,
    onPressed: () -> Unit
) {
    Surface(
        onClick = onPressed,
        modifier = modifier.padding(horizontal = 32.dp),
        border = ClickableSurfaceDefaults.border(
            border = Border(
                border = BorderStroke(3.dp, Color.White),
                shape = CircleShape,
            ),
            focusedBorder = Border(
                border = BorderStroke(5.dp, Color.White),
                shape = CircleShape,
            ),
        ),
        shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
        scale = ClickableSurfaceDefaults.scale(focusedScale = 1.5f),
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =  Modifier.size(100.dp).clip(CircleShape).background(Color.Transparent),
        )
    }
}