package com.dreamsoftware.player.domain.state

interface PlayerStateListener {
    fun on(state: PlayerState)
}