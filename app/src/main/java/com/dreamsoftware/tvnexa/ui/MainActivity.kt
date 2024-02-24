package com.dreamsoftware.tvnexa.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dreamsoftware.tvnexa.ui.features.app.AppScreen
import com.dreamsoftware.tvnexa.ui.receiver.ScreenStateReceiver
import com.dreamsoftware.tvnexa.utils.network.NetworkConnectivityMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var screenStateReceiver: ScreenStateReceiver

    @Inject
    lateinit var networkConnectivityMonitor: NetworkConnectivityMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        screenStateReceiver = ScreenStateReceiver.register(this)
        networkConnectivityMonitor.registerNetworkCallback()
        setContent {
            AppScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ScreenStateReceiver.unregister(this, screenStateReceiver)
        networkConnectivityMonitor.unregisterNetworkCallback()
    }
}
