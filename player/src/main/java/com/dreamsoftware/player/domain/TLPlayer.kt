package com.dreamsoftware.player.domain

import android.view.View
import androidx.annotation.RawRes
import com.dreamsoftware.player.domain.state.PlayerStateListener

interface TLPlayer {
    fun play()
    fun pause()
    fun stop()

    fun seekTo(positionMs: Long)
    fun seekForward()
    fun seekBack()

    fun prepare(videHlsResource: String)
    fun prepare(@RawRes videoResourceId: Int)

    fun release()
    fun getView(): View

    val currentPosition: Long
    val duration: Long
    val isPlaying: Boolean

    fun setPlaybackEvent(callback: PlayerStateListener)
    fun removePlaybackEvent(callback: PlayerStateListener)
}