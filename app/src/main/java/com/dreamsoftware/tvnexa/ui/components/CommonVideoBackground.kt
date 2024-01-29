package com.dreamsoftware.tvnexa.ui.components

import android.annotation.SuppressLint
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
import com.dreamsoftware.player.domain.SupportPlayer
import com.dreamsoftware.player.domain.state.PlayerStateListener
import com.dreamsoftware.tvnexa.ui.extensions.handleDPadKeyEvents
import com.dreamsoftware.player.impl.PlayerFactory
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@SuppressLint("OpaqueUnitKey", "UnsafeOptInUsageError")
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


private fun disableCertificateValidation() {
    try {
        val trustAllCerts = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
        HttpsURLConnection.setDefaultHostnameVerifier { _, _ -> true }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}