package com.dreamsoftware.tvnexa.ui.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.view.KeyEvent
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onPreviewKeyEvent
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.AvatarTypeEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow

private val DPadEventsKeyCodes = listOf(
    KeyEvent.KEYCODE_DPAD_LEFT,
    KeyEvent.KEYCODE_SYSTEM_NAVIGATION_LEFT,
    KeyEvent.KEYCODE_DPAD_RIGHT,
    KeyEvent.KEYCODE_SYSTEM_NAVIGATION_RIGHT,
    KeyEvent.KEYCODE_DPAD_CENTER,
    KeyEvent.KEYCODE_ENTER,
    KeyEvent.KEYCODE_NUMPAD_ENTER,
)

fun Modifier.handleNumericKeyEvents(
    onNumericKeyPressed: (Int) -> Unit
) = onPreviewKeyEvent { event ->
    val numericKeyMap = mapOf(
        KeyEvent.KEYCODE_0 to 0,
        KeyEvent.KEYCODE_1 to 1,
        KeyEvent.KEYCODE_2 to 2,
        KeyEvent.KEYCODE_3 to 3,
        KeyEvent.KEYCODE_4 to 4,
        KeyEvent.KEYCODE_5 to 5,
        KeyEvent.KEYCODE_6 to 6,
        KeyEvent.KEYCODE_7 to 7,
        KeyEvent.KEYCODE_8 to 8,
        KeyEvent.KEYCODE_9 to 9
    )

    val keyCode = event.nativeKeyEvent.keyCode
    val numericKeyCode = numericKeyMap[keyCode]

    if (numericKeyCode != null && event.nativeKeyEvent.repeatCount == 0) {
        onNumericKeyPressed(numericKeyCode)
        true
    } else {
        false
    }
}

fun Modifier.handleDPadKeyEvents(
    onLeft: (() -> Unit)? = null,
    onRight: (() -> Unit)? = null,
    onEnter: (() -> Unit)? = null,
) = onPreviewKeyEvent {
    fun onActionUp(block: () -> Unit) {
        if (it.nativeKeyEvent.action == KeyEvent.ACTION_UP) block()
    }

    if (!DPadEventsKeyCodes.contains(it.nativeKeyEvent.keyCode)) return@onPreviewKeyEvent false

    when (it.nativeKeyEvent.keyCode) {
        KeyEvent.KEYCODE_ENTER,
        KeyEvent.KEYCODE_DPAD_CENTER,
        KeyEvent.KEYCODE_NUMPAD_ENTER,
        -> {
            onEnter?.apply {
                onActionUp(::invoke)
                return@onPreviewKeyEvent true
            }
        }

        KeyEvent.KEYCODE_DPAD_LEFT,
        KeyEvent.KEYCODE_SYSTEM_NAVIGATION_LEFT,
        -> {
            onLeft?.apply {
                onActionUp(::invoke)
                return@onPreviewKeyEvent true
            }
        }

        KeyEvent.KEYCODE_DPAD_RIGHT,
        KeyEvent.KEYCODE_SYSTEM_NAVIGATION_RIGHT,
        -> {
            onRight?.apply {
                onActionUp(::invoke)
                return@onPreviewKeyEvent true
            }
        }
    }
    false
}

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '

fun AvatarTypeEnum.toDrawableResource(): Int = when(this) {
    AvatarTypeEnum.BOY -> R.drawable.profile_avatar_boy
    AvatarTypeEnum.GIRL -> R.drawable.profile_avatar_girl
    AvatarTypeEnum.WOMAN -> R.drawable.profile_avatar_woman
    AvatarTypeEnum.MAN -> R.drawable.profile_avatar_man
}

fun Context.isAndroidTV(): Boolean =
    packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)

fun Context.restartApplication() {
    packageManager.getLeanbackLaunchIntentForPackage(packageName)?.run {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }?.let(::startActivity)
}

fun Context.openSystemSettings() {
    startActivity(Intent(Settings.ACTION_SETTINGS))
}