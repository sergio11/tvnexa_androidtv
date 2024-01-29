package com.dreamsoftware.tvnexa.ui.features.player

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.player.domain.state.PlayerState
import com.dreamsoftware.player.domain.state.PlayerStateListener
import com.dreamsoftware.tvnexa.ui.components.CommonVideoBackground
import com.dreamsoftware.tvnexa.ui.features.player.controls.PlayerControls
import com.dreamsoftware.tvnexa.ui.features.player.controls.rememberVideoPlayerState
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun PlayerScreen(mediaUrl: String, onBackPressed: () -> Unit) {
    PlayerScreenContent(Modifier, mediaUrl, onBackPressed)
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun PlayerScreenContent(modifier: Modifier, mediaUrl: String, onBackPressed: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    var contentCurrentPosition: Long by remember { mutableLongStateOf(0L) }
    val videoPlayerState = rememberVideoPlayerState(hideSeconds = 4, coroutineScope)

    val stateListener = remember {
        object : PlayerStateListener {
            override fun on(state: PlayerState) {
                Timber.d("State $state")
            }
        }
    }

    BackHandler(onBack = onBackPressed)
    CommonVideoBackground(
        videHlsResource = mediaUrl,
        playerStateListener = stateListener,
        onEnter = {
            if (!videoPlayerState.isDisplayed) {
                coroutineScope.launch {
                    videoPlayerState.showControls()
                }
            }
        }
    ) { player ->
        PlayerControls(
            modifier = Modifier.align(Alignment.BottomCenter),
            isPlaying = player.isPlaying,
            onPlayPauseToggle = { shouldPlay ->
                if (shouldPlay) {
                    player.play()
                } else {
                    player.pause()
                }
            },
            contentProgressInMillis = contentCurrentPosition,
            contentDurationInMillis = player.duration,
            state = videoPlayerState,
            onSeek = { seekProgress ->
                player.seekTo(player.duration.times(seekProgress).toLong())
            },
        )
    }
}