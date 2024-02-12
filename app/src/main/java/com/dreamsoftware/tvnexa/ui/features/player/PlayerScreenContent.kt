package com.dreamsoftware.tvnexa.ui.features.player

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dreamsoftware.player.domain.state.PlayerState
import com.dreamsoftware.player.domain.state.PlayerStateListener
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.ui.components.CommonChannelHeaderInfo
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground
import com.dreamsoftware.tvnexa.ui.features.player.components.PlayerControlsState
import com.dreamsoftware.tvnexa.ui.features.player.components.VideoPlayerControlsIcon
import com.dreamsoftware.tvnexa.ui.features.player.components.rememberVideoPlayerState
import kotlinx.coroutines.launch
import timber.log.Timber

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun PlayerScreenContent(
    uiState: PlayerUiState
) {

    val coroutineScope = rememberCoroutineScope()
    var playerState: PlayerState by remember { mutableStateOf(PlayerState.Idle) }
    val videoPlayerState = rememberVideoPlayerState(hideSeconds = 4, coroutineScope)
    val stateListener = remember {
        object : PlayerStateListener {
            override fun on(state: PlayerState) {
                Timber.d("State $state")
                playerState = state
            }
        }
    }

    uiState.channelDetail?.let { channel ->
        CommonVideoBackground(
            videHlsResource = channel.streamUrl,
            playerStateListener = stateListener,
            onEnter = {
                if (!videoPlayerState.isDisplayed) {
                    coroutineScope.launch {
                        videoPlayerState.showControls()
                    }
                }
            }
        ) {
            PlayerControls(
                modifier = Modifier.align(Alignment.BottomCenter),
                channelBO = channel,
                isPlaying = playerState is PlayerState.Playing,
                state = videoPlayerState
            )
        }
    }
}

@Composable
private fun PlayerControls(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    state: PlayerControlsState,
    channelBO: ChannelDetailBO
) {
    LaunchedEffect(isPlaying) {
        if (!isPlaying) {
            state.showControls(seconds = Int.MAX_VALUE)
        } else {
            state.showControls()
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = state.isDisplayed,
        enter = expandVertically { it },
        exit = shrinkVertically { it },
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black,
                            Color.Transparent,
                            Color.Black,
                        ),
                    ),
                )
                .padding(
                    horizontal = 56.dp,
                    vertical = 32.dp,
                ),
        ) {
            PlayerContentHeaders(
                channelBO = channelBO
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CommonFocusRequester { focusRequester ->
                    VideoPlayerControlsIcon(
                        modifier = Modifier.focusRequester(focusRequester),
                        icon = R.drawable.ic_auto_awesome_motion,
                        state = state,
                        isPlaying = isPlaying,
                        contentDescription = "Playlist",
                    )
                }
                VideoPlayerControlsIcon(
                    modifier = Modifier.padding(start = 12.dp),
                    icon = R.drawable.ic_settings,
                    state = state,
                    isPlaying = isPlaying,
                    contentDescription = "Settings Icon",
                )
            }
        }
    }
}

@Composable
private fun PlayerContentHeaders(
    channelBO: ChannelDetailBO
) {
    with(channelBO) {
        CommonChannelHeaderInfo(
            name = name,
            logo = logo,
            city = city,
            isNsfw = isNsfw
        )
    }
}