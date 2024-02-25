@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.CommonChannelsLCE
import com.dreamsoftware.tvnexa.ui.components.CommonScreenContent
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum

private const val CHANNELS_GRID_COLUMN_COUNT = 5

@Composable
fun FavoritesScreenContent(
    uiState: FavoritesUiState,
    onChannelPressed: (SimpleChannelBO) -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        CommonScreenContent(
            error = error,
            onErrorAccepted = onErrorAccepted
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
            ) {
                GridHeader()
                CommonChannelsLCE(
                    gridColumnCount = CHANNELS_GRID_COLUMN_COUNT,
                    noResultFoundTitleRes = R.string.favorites_screen_no_channels_found_text,
                    loadingResultsTitleRes = R.string.favorites_screen_loading,
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
private fun GridHeader() {
    CommonText(
        type = CommonTextTypeEnum.TITLE_LARGE,
        modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp),
        titleRes = R.string.favorites_screen_main_title,
        textColor = MaterialTheme.colorScheme.primary,
    )
}