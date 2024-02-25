package com.dreamsoftware.tvnexa.ui.features.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.CommonChannelsLCE
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
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
        Row(
            modifier = Modifier.fillMaxSize().padding(vertical = 24.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
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
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                GridHeader(term)
                CommonChannelsLCE(
                    loadingResultsTitleRes = R.string.search_screen_search_results_loading,
                    noResultFoundTitleRes = R.string.search_screen_search_no_results_found,
                    isLoading = isLoading,
                    channels = channels,
                    error = error,
                    onChannelPressed = onChannelPressed
                )
            }
        }
    }
}

@Composable
private fun GridHeader(term: String) {
    CommonText(
        titleText = if(term.isNotBlank()) {
            stringResource(id = R.string.search_screen_search_results_title_with_term, term)
        } else {
            stringResource(id = R.string.search_screen_search_results_title)
        },
        type = CommonTextTypeEnum.TITLE_LARGE,
        textColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp),
    )
}

@Composable
private fun SearchView() {
    with(MaterialTheme.colorScheme) {
        Column {
            CommonText(
                titleRes = R.string.search_screen_main_title,
                type = CommonTextTypeEnum.TITLE_LARGE,
                textColor = primary,
                modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp),
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .background(onSurface))
        }
    }
}