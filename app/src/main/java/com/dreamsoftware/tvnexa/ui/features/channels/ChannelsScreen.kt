package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.compose.runtime.Composable

@Composable
fun ChannelsScreen(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit,
) {
    ChannelScreenContent(
        onItemFocus = onItemFocus,
        onItemClick = onItemClick
    )
}



