package com.dreamsoftware.tvnexa.utils.network

import android.net.ConnectivityManager
import android.net.Network
import com.dreamsoftware.tvnexa.utils.AppEvent
import com.dreamsoftware.tvnexa.utils.AppEventBus
import timber.log.Timber

class NetworkConnectivityCallback(private val appEventBus: AppEventBus) : ConnectivityManager.NetworkCallback() {

    private var lastState: Boolean = true

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Timber.tag(TAG).d("Network available")
        updateConnectivityState(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Timber.tag(TAG).d("Network lost")
        updateConnectivityState(false)
    }

    private fun updateConnectivityState(newState: Boolean) {
        if (newState != lastState) {
            appEventBus.send(
                AppEvent.NetworkConnectivityStateChanged(
                    lastState = lastState,
                    newState = newState
                )
            ).also {
                lastState = newState
            }
        }
    }

    companion object {
        private const val TAG = "NetworkConnectivity"
    }
}