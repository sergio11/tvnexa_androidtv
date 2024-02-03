package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.OptIn
import androidx.annotation.RawRes
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.dreamsoftware.player.domain.SupportPlayer
import com.dreamsoftware.player.domain.state.PlayerStateListener
import com.dreamsoftware.tvnexa.ui.extensions.handleDPadKeyEvents
import com.dreamsoftware.player.impl.PlayerFactory
import androidx.compose.runtime.State
import androidx.media3.common.util.UnstableApi
import com.dreamsoftware.tvnexa.utils.disableCertificateValidation


/**
 * Composable function for a common video background with player controls.
 *
 * @param modifier Modifier for the video background.
 * @param videHlsResource HLS video resource URL.
 * @param videoResourceId Raw resource ID for the video.
 * @param disableCertValidation Flag to disable certificate validation.
 * @param playerStateListener Listener for player state events.
 * @param onLeft Callback for left D-pad key press.
 * @param onRight Callback for right D-pad key press.
 * @param onEnter Callback for enter key press.
 * @param content Lambda to define additional content inside the BoxScope.
 */
@OptIn(UnstableApi::class)
@Composable
fun CommonVideoBackground(
    modifier: Modifier = Modifier,
    videHlsResource: String? = null,
    @RawRes videoResourceId: Int? = null,
    disableCertValidation: Boolean = true,
    playerStateListener: PlayerStateListener? = null,
    onLeft: (() -> Unit)? = null,
    onRight: (() -> Unit)? = null,
    onEnter: (() -> Unit)? = null,
    content: @Composable BoxScope.(supportPlayer: SupportPlayer) -> Unit = {}
) {
    if (disableCertValidation) {
        disableCertificateValidation()
    }

    val context = LocalContext.current
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val player = remember { PlayerFactory.create(context) }

    InitializeAndPlayVideo(
        lifecycleOwner = lifecycleOwner,
        player = player,
        videHlsResource = videHlsResource,
        videoResourceId = videoResourceId,
        playerStateListener = playerStateListener
    )

    PlayerViewContent(
        modifier = modifier,
        player = player,
        onLeft = onLeft,
        onRight = onRight,
        onEnter = onEnter,
        content = content
    )
}

/**
 * Helper function to initialize and play the video based on lifecycle events.
 *
 * @param lifecycleOwner State holding the current LifecycleOwner.
 * @param player SupportPlayer instance for video playback.
 * @param videHlsResource HLS video resource URL.
 * @param videoResourceId Raw resource ID for the video.
 * @param playerStateListener Listener for player state events.
 */
@Composable
private fun InitializeAndPlayVideo(
    lifecycleOwner: State<LifecycleOwner>,
    player: SupportPlayer,
    videHlsResource: String? = null,
    @RawRes videoResourceId: Int? = null,
    playerStateListener: PlayerStateListener? = null
) {
    val playContent = {
        with(player) {
            videHlsResource?.let {
                prepare(videHlsResource)
            } ?: run {
                videoResourceId?.let {
                    prepare(it)
                }
            }
            play()
        }
    }

    playerStateListener?.let { stateListener ->
        LaunchedEffect(Unit) {
            player.setPlaybackEvent(callback = stateListener)
        }
    }

    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    player.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    playContent()
                }
                else -> {}
            }
        }
        val lifecycle = lifecycleOwner.value.lifecycle
        lifecycle.addObserver(observer)
        onDispose {
            player.release()
            lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(videHlsResource ?: videoResourceId) {
        val lifecycle = lifecycleOwner.value.lifecycle
        if (lifecycle.currentState == Lifecycle.State.RESUMED) {
            playContent()
        }
    }
}


/**
 * Composable function to display the player view content within a Box.
 *
 * @param modifier Modifier for the player view content.
 * @param player SupportPlayer instance for video playback.
 * @param onLeft Callback for left D-pad key press.
 * @param onRight Callback for right D-pad key press.
 * @param onEnter Callback for enter key press.
 * @param content Lambda to define additional content inside the BoxScope.
 */
@Composable
private fun PlayerViewContent(
    modifier: Modifier = Modifier,
    player: SupportPlayer,
    onLeft: (() -> Unit)? = null,
    onRight: (() -> Unit)? = null,
    onEnter: (() -> Unit)? = null,
    content: @Composable BoxScope.(supportPlayer: SupportPlayer) -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(factory = { player.getView() }, modifier = Modifier
            .fillMaxSize()
            .handleDPadKeyEvents(
                onEnter = onEnter,
                onLeft = onLeft,
                onRight = onRight
            )
            .focusable())
        content(player)
    }
}