@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.ChannelGridItem
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonLoading
import com.dreamsoftware.tvnexa.ui.components.CommonScreenContent
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ErrorStateNotificationComponent

@Composable
fun FavoritesScreenContent(
    uiState: FavoritesUiState,
    onChannelPressed: (SimpleChannelBO) -> Unit,
) {
    with(uiState) {
        CommonScreenContent(
            error = error
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                when {
                    isLoading -> CommonLoading(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = R.string.favorites_screen_loading
                    )
                    channels.isEmpty() || !error.isNullOrBlank() -> ErrorStateNotificationComponent(
                        imageRes = R.drawable.default_placeholder,
                        title = if(channels.isEmpty()) {
                            stringResource(id = R.string.favorites_screen_no_channels_found_text)
                        } else {
                            error.orEmpty()
                        }
                    )
                    else -> ContentGrid(
                        channels = channels,
                        onChannelPressed = onChannelPressed
                    )
                }
            }
        }
    }
}

@Composable
private fun ContentGrid(
    channels: List<SimpleChannelBO>,
    onChannelPressed: (SimpleChannelBO) -> Unit,
) {
    CommonFocusRequester { focusRequester ->
        TvLazyVerticalGrid(
            columns = TvGridCells.Fixed(5),
            contentPadding = PaddingValues(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 48.dp),
        ) {
            item(span = {
                TvGridItemSpan(5)
            }) {
                GridHeader()
            }
            items(channels.size) { idx ->
                ChannelGridItem(
                    modifier = if(idx == 0) {
                        Modifier.focusRequester(focusRequester)
                    } else {
                        Modifier
                    },
                    channel = channels[idx],
                    onChannelPressed = onChannelPressed
                )
            }
        }
    }
}

@Composable
private fun GridHeader() {
    CommonText(
        type = CommonTextTypeEnum.TITLE_LARGE,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp),
        titleRes = R.string.favorites_screen_main_title,
        textColor = MaterialTheme.colorScheme.primary,
    )
}