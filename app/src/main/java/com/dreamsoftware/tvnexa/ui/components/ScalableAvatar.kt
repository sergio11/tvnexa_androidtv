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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface

private val DEFAULT_HORIZONTAL_PADDING = 32.dp
private val DEFAULT_AVATAR_SIZE = 100.dp
private const val DEFAULT_FOCUSED_SCALE = 1.5f
private val DEFAULT_AVATAR_BORDER_COLOR = Color.White

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ScalableAvatar(
    modifier: Modifier = Modifier,
    size: Dp = DEFAULT_AVATAR_SIZE,
    padding: Dp = DEFAULT_HORIZONTAL_PADDING,
    focusedScale: Float = DEFAULT_FOCUSED_SCALE,
    borderColor: Color = DEFAULT_AVATAR_BORDER_COLOR,
    @DrawableRes avatarRes: Int,
    onPressed: () -> Unit = {}
) {
    Surface(
        onClick = onPressed,
        modifier = modifier.padding(horizontal = padding),
        border = ClickableSurfaceDefaults.border(
            border = Border(
                border = BorderStroke(3.dp, borderColor),
                shape = CircleShape,
            ),
            focusedBorder = Border(
                border = BorderStroke(5.dp, borderColor),
                shape = CircleShape,
            ),
        ),
        shape = ClickableSurfaceDefaults.shape(shape = CircleShape),
        scale = ClickableSurfaceDefaults.scale(focusedScale = focusedScale),
    ) {
        Image(
            painter = painterResource(id = avatarRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =  Modifier
                .size(size)
                .clip(CircleShape)
                .background(Color.Transparent),
        )
    }
}