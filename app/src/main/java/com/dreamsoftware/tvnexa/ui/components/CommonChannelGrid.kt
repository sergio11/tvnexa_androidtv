package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO

@Composable
fun CommonChannelGrid(
    modifier: Modifier = Modifier,
    gridColumnCount: Int,
    channels: List<SimpleChannelBO>,
    onChannelPressed: (SimpleChannelBO) -> Unit,
) {
    CommonFocusRequester { focusRequester ->
        TvLazyVerticalGrid(
            modifier = modifier,
            columns = TvGridCells.Fixed(gridColumnCount),
            contentPadding = PaddingValues(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 48.dp),
        ) {
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