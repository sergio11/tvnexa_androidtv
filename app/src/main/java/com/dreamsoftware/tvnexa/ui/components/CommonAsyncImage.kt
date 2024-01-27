@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.dreamsoftware.tvnexa.R

@Composable
fun CommonAsyncImage(
    modifier: Modifier,
    context: Context,
    reverseStyle: Boolean = false,
    imageUrl: String? = null,
    imageUri: Uri? = null,
    contentScale: ContentScale = ContentScale.Crop,
    colorFilter: ColorFilter? = null
) {
    (imageUrl ?: imageUri)?.let {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(it)
                .crossfade(true)
                .build(),
            contentScale = contentScale,
            contentDescription = stringResource(R.string.image_content_description),
            colorFilter = colorFilter
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                DefaultImagePlaceholder(modifier, reverseStyle)
            } else {
                SubcomposeAsyncImageContent(modifier)
            }
        }
    } ?: run {
        DefaultImagePlaceholder(modifier, reverseStyle)
    }
}

@Composable
private fun DefaultImagePlaceholder(
    modifier: Modifier,
    reverseStyle: Boolean = false
) {
    with(MaterialTheme.colorScheme) {
        Image(
            painter = painterResource(R.drawable.default_placeholder),
            colorFilter = ColorFilter.tint(if(reverseStyle) {
                surface
            } else {
                primary
            }),
            contentDescription = stringResource(R.string.image_content_description),
            contentScale = ContentScale.Fit,
            modifier = modifier.background(if(reverseStyle) {
                primary
            } else {
                surface
            })
        )
    }
}