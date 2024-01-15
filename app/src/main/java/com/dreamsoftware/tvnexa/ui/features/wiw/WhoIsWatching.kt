package com.dreamsoftware.tvnexa.ui.features.wiw

import androidx.compose.runtime.Composable
import com.dreamsoftware.tvnexa.ui.features.wiw.data.Avatar

@Composable
fun WhoIsWatchingScreen(onProfileSelection: (avatar: Avatar) -> Unit) {
    WhoIsWatchingContent(onProfileSelection)
}
