package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO

private const val DEFAULT_GRID_COLUMN_COUNT = 3

@Composable
fun CommonChannelsLCE(
    @StringRes noResultFoundTitleRes: Int,
    @StringRes loadingResultsTitleRes: Int,
    gridColumnCount: Int = DEFAULT_GRID_COLUMN_COUNT,
    isLoading: Boolean,
    channels: List<SimpleChannelBO>,
    error: String?,
    onChannelPressed: (SimpleChannelBO) -> Unit,
) {
    Column {
        when {
            isLoading -> LoadingContent(titleRes = loadingResultsTitleRes)
            channels.isEmpty() || !error.isNullOrBlank() -> EmptyOrErrorContent(
                titleRes = noResultFoundTitleRes,
                isEmpty = channels.isEmpty(),
                error = error
            )
            else -> CommonChannelGrid(
                gridColumnCount = gridColumnCount,
                channels = channels,
                onChannelPressed = onChannelPressed
            )
        }
    }
}

@Composable
private fun LoadingContent(@StringRes titleRes: Int) {
    CommonLoading(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = titleRes
    )
}

@Composable
private fun EmptyOrErrorContent(
    @StringRes titleRes: Int,
    isEmpty: Boolean,
    error: String?
) {
    ErrorStateNotificationComponent(
        imageRes = R.drawable.default_placeholder,
        title = if (isEmpty) {
            stringResource(id = titleRes)
        } else {
            error.orEmpty()
        }
    )
}