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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.ChannelGridItem
import com.dreamsoftware.tvnexa.ui.components.CommonLoading
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ErrorStateNotificationComponent
import com.dreamsoftware.tvnexa.ui.components.MiniKeyboard

@Composable
fun SearchScreenContent(
    uiState: SearchUiState,
    onChannelPressed: (SimpleChannelBO) -> Unit,
    onKeyPressed: (String) -> Unit,
    onSearchPressed: () -> Unit,
    onClearPressed: () -> Unit,
    onBackSpacePressed: () -> Unit,
    onSpaceBarPressed: () -> Unit
) {
    with(uiState) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)) {
                SearchView()
                MiniKeyboard(
                    modifier = Modifier.width(300.dp),
                    onKeyPressed = onKeyPressed,
                    onSearchPressed = onSearchPressed,
                    onClearPressed = onClearPressed,
                    onBackSpacePressed = onBackSpacePressed,
                    onSpaceBarPressed = onSpaceBarPressed
                )
            }
            when {
                isLoading -> CommonLoading(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = R.string.search_screen_search_results_loading
                )
                !error.isNullOrBlank() -> ErrorStateNotificationComponent(imageRes = R.drawable.default_placeholder, title = error as String)
                else -> ContentGrid(
                    term = term,
                    channels = channels,
                    onChannelPressed = onChannelPressed
                )
             }
        }
    }
}

@Composable
fun ContentGrid(
    modifier: Modifier = Modifier,
    channels: List<SimpleChannelBO>,
    term: String,
    onChannelPressed: (SimpleChannelBO) -> Unit,
) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Fixed(3),
        contentPadding = PaddingValues(start = 12.dp, top = 24.dp, end = 12.dp, bottom = 48.dp),
    ) {
        item(span = {
            TvGridItemSpan(3)
        }) {
            GridHeader(term)
        }
        items(channels.size) { idx ->
            ChannelGridItem(
                channel = channels[idx],
                onChannelPressed = onChannelPressed
            )
        }
    }
}

@Composable
fun GridHeader(term: String) {
    CommonText(
        titleText = if(term.isNotBlank()) {
            stringResource(id = R.string.search_screen_search_results_title_with_term, term)
        } else {
            stringResource(id = R.string.search_screen_search_results_title)
        },
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
                titleRes = R.string.search_screen_main_title,
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