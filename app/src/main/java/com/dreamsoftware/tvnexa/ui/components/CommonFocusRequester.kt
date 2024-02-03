package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

private const val DEFAULT_REQUEST_FOCUS_AT_IN_MILLIS = 1000L

@Composable
fun CommonFocusRequester(
    requestFocusAtInMillis: Long = DEFAULT_REQUEST_FOCUS_AT_IN_MILLIS,
    shouldRequestFocus: () -> Boolean = { true },
    content: @Composable (FocusRequester) -> Unit
) {
    val requester = remember { FocusRequester() }
    if(shouldRequestFocus()) {
        LaunchedEffect(Unit) {
            delay(requestFocusAtInMillis)
            requester.requestFocus()
        }
    }
    content(requester)
}