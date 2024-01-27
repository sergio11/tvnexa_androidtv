package com.dreamsoftware.tvnexa.ui.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.ChannelGridItem
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.MiniKeyboard

@Composable
fun SearchScreenContent(
    uiState: SearchUiState
) {
    with(uiState) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)) {
                SearchView()
                MiniKeyboard(modifier = Modifier.width(300.dp))
            }
            ContentGrid(channels = channels)
        }
    }
}

@Composable
fun ContentGrid(
    modifier: Modifier = Modifier,
    channels: List<SimpleChannelBO>
) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Fixed(3),
        contentPadding = PaddingValues(start = 12.dp, top = 24.dp, end = 12.dp, bottom = 48.dp),
    ) {
        item(span = {
            TvGridItemSpan(3)
        }) {
            GridHeader()
        }
        items(channels.size) { idx ->
            ChannelGridItem(channel = channels[idx])
        }
    }
}

@Composable
fun GridHeader() {
    CommonText(
        titleText = "Search Results",
        type = CommonTextTypeEnum.TITLE_LARGE,
        textColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 24.dp, start = 8.dp),
    )
}

@Composable
fun SearchView() {
    with(MaterialTheme.colorScheme) {
        Column {
            CommonText(
                titleText = "Start typing to search",
                type = CommonTextTypeEnum.TITLE_LARGE,
                textColor = primary,
                modifier = Modifier.padding(all = 12.dp),
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(onSurface))
        }
    }
}