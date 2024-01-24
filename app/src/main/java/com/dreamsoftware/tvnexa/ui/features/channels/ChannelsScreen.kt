package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChannelsScreen(
    viewModel: ChannelsViewModel = hiltViewModel(),
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit,
) {
    ChannelScreenContent(
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}



