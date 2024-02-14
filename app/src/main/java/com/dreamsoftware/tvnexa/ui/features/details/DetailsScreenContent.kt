@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.ui.components.ChannelLogo
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonFavoriteButton
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground

@Composable
fun DetailsScreenContent(
    uiState: DetailUiState,
    onPlayChannelPressed: (channelId: String) -> Unit
) {
    with(uiState) {
        with(MaterialTheme.colorScheme) {
            Box {
                SearchIcon(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.TopStart)
                        .padding(24.dp)
                        .zIndex(1f),
                )
                DetailChannelPreview(channelDetail)
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(color = surface.copy(alpha = 0.90f)),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    DetailActionsSection {
                        channelDetail?.channelId?.let(onPlayChannelPressed)
                    }
                    DetailsSection(channelDetail)
                }
                DetailChannelLogo(channelDetail)
            }
        }
    }
}

@Composable
fun SearchIcon(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "search",
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun DetailChannelPreview(channelDetail: ChannelDetailBO?) {
    Box(modifier = Modifier
        .fillMaxSize()) {
        channelDetail?.streamUrl?.let {
            CommonVideoBackground(
                videHlsResource = it
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    )
}

@Composable
private fun BoxScope.DetailChannelLogo(channelDetail: ChannelDetailBO?) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 30.dp)
    ) {
        ChannelLogo(
            size = 120.dp,
            logo = channelDetail?.logo
        )
    }
}

@Composable
private fun DetailActionsSection(onPlayChannelPressed: () -> Unit) {
    CommonFocusRequester { requester ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            CommonFavoriteButton()
            CommonButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .focusRequester(requester),
                onClick = onPlayChannelPressed,
                textRes = R.string.detail_content_fullscreen_live_button_title
            )
        }
    }
}

@Composable
fun DetailsSection(channelDetail: ChannelDetailBO?) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.size(220.dp))
        Column(
            Modifier
                .fillMaxWidth()
                // .weight(.7f)
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CommonText(
                type = CommonTextTypeEnum.HEADLINE_LARGE,
                textColor = MaterialTheme.colorScheme.onSurface,
                titleText = channelDetail?.name,
            )

            Spacer(modifier = Modifier.size(10.dp))

            MovieInfoSection()

            Spacer(modifier = Modifier.size(10.dp))
            CommonText(
                type = CommonTextTypeEnum.BODY_LARGE,
                textColor = MaterialTheme.colorScheme.onSurface,
                titleRes = R.string.movie_desciption,
            )
        }
    }
}

@Composable
fun MovieInfoSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier,
            color = LocalContentColor.current,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.release_year),
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            modifier = Modifier,
            color = LocalContentColor.current,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(R.string.movie_duration_text),
        )
        Spacer(modifier = Modifier.size(12.dp))
        Rating(rating = "8.3")
    }
}

@Composable
fun Rating(rating: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "search",
        )
        Spacer(modifier = Modifier.size(8.dp))
        CommonText(
            textAlign = TextAlign.Start,
            type = CommonTextTypeEnum.BODY_LARGE,
            titleText = rating,
        )
    }
}