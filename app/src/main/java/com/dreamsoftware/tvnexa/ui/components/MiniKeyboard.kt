@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

private val alphabet = lazy { ('A'..'Z').toList() }
private val specialCharV1 = lazy { listOf("-", "'") }
private val alphabetLower = lazy { ('a'..'z').toList() }
private val numbers = lazy { ('0'..'9').toList() }

@Composable
fun MiniKeyboard(
    modifier: Modifier,
    onKeyPressed: (String) -> Unit = {},
    onSearchPressed: () -> Unit = {},
    onClearPressed: () -> Unit = {},
    onBackSpacePressed: () -> Unit = {},
    onSpaceBarPressed: () -> Unit = {},
) {
    var sizeInDp by remember { mutableStateOf(DpSize.Zero) }
    var switchKeyboardNumbers by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val extrasHeight by remember { derivedStateOf { sizeInDp.width / 7 } }

    Row(modifier = Modifier.padding(8.dp)) {
        TvLazyVerticalGrid(
            modifier = modifier
                .onSizeChanged {
                    sizeInDp = density.run {
                        DpSize(
                            it.width.toDp(),
                            it.height.toDp(),
                        )
                    }
                },
            columns = TvGridCells.Fixed(7),
        ) {
            val keys =  if(switchKeyboardNumbers) {
                numbers.value + alphabetLower.value
            } else {
                alphabet.value + specialCharV1.value
            }
            items(keys) {
                val key = it.toString()
                KeyItem(key = key, onKeyPressed = { onKeyPressed(key) })
            }
            item(span = { TvGridItemSpan(2) }) {
                KeyItem(
                    modifier = Modifier.aspectRatio(2f),
                    onKeyPressed = onSpaceBarPressed
                ) {
                    Icon(imageVector = Icons.Default.SpaceBar,
                        contentDescription = "Backspace"
                    )
                }
            }
            item(span = { TvGridItemSpan(2) }) {
                KeyItem(
                    modifier = Modifier.aspectRatio(2f),
                    onKeyPressed = onBackSpacePressed
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardBackspace,
                        contentDescription = "Backspace",
                    )
                }
            }
            item(span = { TvGridItemSpan(2) }) {
                KeyItem(
                    modifier = Modifier.aspectRatio(2f),
                    onKeyPressed = onSearchPressed
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    )
                }
            }
        }
        TvLazyColumn {
            item {
                KeyItem(
                    modifier = Modifier
                        .width(extrasHeight * 1.5f)
                        .height(extrasHeight),
                    onKeyPressed = onClearPressed
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Cancel",
                    )
                }
            }
            item {
                KeyItem(
                    modifier = Modifier
                        .width(extrasHeight * 1.5f)
                        .height(extrasHeight),
                    onKeyPressed = {
                        switchKeyboardNumbers = !switchKeyboardNumbers
                    }
                ) {
                    Text(text = "&123")
                }
            }
        }
    }
}

@Composable
private fun KeyItem(
    modifier: Modifier = Modifier,
    key: String,
    onKeyPressed: () -> Unit = {}
) {
    KeyItem(
        modifier = modifier.aspectRatio(1f),
        onKeyPressed = onKeyPressed
    ) {
        Text(text = key)
    }
}

@Composable
private fun KeyItem(
    modifier: Modifier = Modifier,
    onKeyPressed: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.padding(3.dp),
        onClick = onKeyPressed,
        shape = ClickableSurfaceDefaults.shape(shape = MaterialTheme.shapes.small),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Preview
@Composable
fun MiniKeyboardPrev() {
    TvNexaTheme {
        MiniKeyboard(modifier = Modifier.size(400.dp))
    }
}
