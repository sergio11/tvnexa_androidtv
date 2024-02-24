@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.details

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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.ui.components.ChannelLogo
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonFavoriteButton
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonInfoRow
import com.dreamsoftware.tvnexa.ui.components.CommonScreenContent
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground
import com.dreamsoftware.tvnexa.ui.theme.Dimens

@Composable
fun DetailsScreenContent(
    uiState: DetailUiState,
    onFavoriteStateChanged: (Boolean) -> Unit,
    onPlayChannelPressed: (channelId: String) -> Unit
) {
    with(uiState) {
        CommonScreenContent(
            error = error
        ) {
            Box {
                ChannelDetailPreview(channelDetail = channelDetail)
                ChannelDetailInfo(
                    uiState = uiState,
                    onFavoriteStateChanged = onFavoriteStateChanged,
                    onPlayChannelPressed = onPlayChannelPressed
                )
                DetailChannelLogo(channelDetail = channelDetail)
            }
        }
    }
}

@Composable
private fun ChannelDetailPreview(channelDetail: ChannelDetailBO?) {
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
private fun BoxScope.ChannelDetailInfo(
    uiState: DetailUiState,
    onFavoriteStateChanged: (Boolean) -> Unit,
    onPlayChannelPressed: (channelId: String) -> Unit
) {
    with(uiState) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.90f)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row{
                Spacer(modifier = Modifier.width(150.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.DETAIL_HORIZONTAL_SPACING),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ChannelDetailHeader(channel = channelDetail)
                    DetailActionsSection(
                        isFavorite = isSavedInFavorites,
                        onFavoriteStateChanged = onFavoriteStateChanged,
                    ) {
                        channelDetail?.channelId?.let(onPlayChannelPressed)
                    }
                }
            }
            channelDetail?.let {
                Spacer(modifier = Modifier.height(Dimens.DETAIL_VERTICAL_SPACING))
                ChannelDetailsInfo(it)
            }
        }
    }
}

@Composable
private fun BoxScope.DetailChannelLogo(channelDetail: ChannelDetailBO?) {
    Box(
        modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = Dimens.DETAIL_HORIZONTAL_SPACING)
    ) {
        ChannelLogo(
            size = 120.dp,
            logo = channelDetail?.logo
        )
    }
}

@Composable
private fun DetailActionsSection(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onFavoriteStateChanged: (Boolean) -> Unit,
    onPlayChannelPressed: () -> Unit
) {
    CommonFocusRequester { requester ->
        Row(
            modifier = modifier
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            CommonFavoriteButton(
                isFavorite = isFavorite,
                onStateChanged = onFavoriteStateChanged
            )
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
private fun ChannelDetailsInfo(channelDetail: ChannelDetailBO) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(140.dp))
        Column {
            CommonInfoRow(
                labelRes = R.string.detail_content_city_label_text,
                value = channelDetail.city ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_subdivision_label_text,
                value = channelDetail.subdivision?.name ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_launched_label_text,
                value = channelDetail.launched ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_broadcast_areas_label_text,
                value = channelDetail.broadcastAreas.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: stringResource(id = R.string.no_text_value),
            )
        }
        Spacer(modifier = Modifier.width(Dimens.DETAIL_HORIZONTAL_SPACING))
        Column {
            CommonInfoRow(
                labelRes = R.string.detail_content_website_label_text,
                value = channelDetail.website ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_alt_names_label_text,
                value = channelDetail.altNames.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_owners_label_text,
                value = channelDetail.owners.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_closed_label_text,
                value = channelDetail.closed ?: stringResource(id = R.string.no_text_value),
            )
        }
        Spacer(modifier = Modifier.width(Dimens.DETAIL_HORIZONTAL_SPACING))
        Column {
            CommonInfoRow(
                labelRes = R.string.detail_content_website_label_text,
                value = channelDetail.categories.map { it.name }.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_languages_label_text,
                value = channelDetail.languages.map { it.name }.takeIf { it.isNotEmpty() }?.joinToString(", ") ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_network_label_text,
                value = channelDetail.network ?: stringResource(id = R.string.no_text_value),
            )
            CommonInfoRow(
                labelRes = R.string.detail_content_guides_label_text,
                value = channelDetail.guides.takeIf { it.isNotEmpty() }?.size?.toString() ?: stringResource(id = R.string.no_text_value),
            )
        }
    }
}

@Composable
private fun ChannelDetailHeader(channel: ChannelDetailBO?) {
    with(MaterialTheme.colorScheme) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CommonText(
                type = CommonTextTypeEnum.HEADLINE_LARGE,
                textColor = onSurface,
                titleText = channel?.name,
            )
            Spacer(modifier = Modifier.size(10.dp))
            CommonText(
                type = CommonTextTypeEnum.TITLE_LARGE,
                titleText = channel?.country?.flag,
                textColor = onSurface
            )
            Spacer(modifier = Modifier.size(5.dp))
            CommonText(
                type = CommonTextTypeEnum.BODY_LARGE,
                titleText = channel?.country?.code,
                textBold = true,
                textColor = onSurface
            )
        }
    }
}
