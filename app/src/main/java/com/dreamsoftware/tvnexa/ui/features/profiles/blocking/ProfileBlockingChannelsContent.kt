package com.dreamsoftware.tvnexa.ui.features.profiles.blocking

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.CommonChannelsLCE
import com.dreamsoftware.tvnexa.ui.components.MiniKeyboard
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent

@Composable
fun ProfileBlockingChannelsContent(
    uiState: ProfileBlockingChannelsUiState,
    onConfirmPressed: () -> Unit,
    onKeyPressed: (String) -> Unit,
    onSearchPressed: () -> Unit,
    onClearPressed: () -> Unit,
    onBackSpacePressed: () -> Unit,
    onSpaceBarPressed: () -> Unit,
    onChannelPressed: (SimpleChannelBO) -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            error = error,
            onErrorAccepted = onErrorAccepted,
            mainTitleRes = R.string.profile_blocking_channels_title,
            secondaryTitleText = if(term.isNotBlank()) {
                stringResource(id = R.string.profile_blocking_channels_title_after_searching, term)
            } else {
                String.EMPTY
            },
            primaryOptionTextRes = R.string.profile_blocking_channels_form_confirm_button_text,
            onPrimaryOptionPressed = onConfirmPressed,
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
            ) {
                MiniKeyboard(
                    modifier = Modifier.width(300.dp),
                    onKeyPressed = onKeyPressed,
                    onSearchPressed = onSearchPressed,
                    onClearPressed = onClearPressed,
                    onBackSpacePressed = onBackSpacePressed,
                    onSpaceBarPressed = onSpaceBarPressed
                )
                CommonChannelsLCE(
                    noResultFoundTitleRes = R.string.profile_blocking_channels_no_results_found,
                    loadingResultsTitleRes = R.string.profile_blocking_channels_search_results_loading,
                    isLoading = isLoading,
                    channels = channels,
                    error = error,
                    onChannelPressed = onChannelPressed
                )
            }
        }
    }
}