package com.dreamsoftware.player.impl

import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import com.dreamsoftware.player.domain.state.PlayerStateListener

@UnstableApi internal class ExoPlayerImpl(
    private val player: ExoPlayer,
    private val providePlayerView: () -> View
) : com.dreamsoftware.player.domain.SupportPlayer {

    private companion object {
        private const val RAW_RESOURCE_SCHEME = "rawresource"
    }

    private var listener: Player.Listener? = null

    override fun play() {
        player.play()
    }

    override fun pause() {
        player.pause()
    }

    override fun stop() {
        player.stop()
    }

    override fun seekTo(positionMs: Long) {
        player.seekTo(positionMs)
    }

    override fun seekForward() {
        player.seekForward()
    }

    override fun seekBack() {
        player.seekBack()
    }

    override fun prepare(videHlsResource: String) {
        player.apply {
            setMediaSource(HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
                .createMediaSource(MediaItem.fromUri(videHlsResource)))
            prepare()
        }
    }

    override fun prepare(videoResourceId: Int) {
        player.apply {
            setMediaItem(MediaItem.fromUri("$RAW_RESOURCE_SCHEME:///$videoResourceId"))
            prepare()
        }
    }

    override fun release() {
        player.release()
    }

    override fun getView(): View  = providePlayerView()

    override val currentPosition: Long
        get() = player.currentPosition

    override val duration: Long
        get() = player.duration

    override val isPlaying: Boolean
        get() = player.isPlaying

    override fun setPlaybackEvent(callback: PlayerStateListener) {
        listener = ExoPlayerStateListener(callback, player).apply {
            player.addListener(this)
        }
    }

    override fun removePlaybackEvent(callback: PlayerStateListener) {
        listener?.let {
            player.removeListener(it)
        }
        listener = null
    }

}