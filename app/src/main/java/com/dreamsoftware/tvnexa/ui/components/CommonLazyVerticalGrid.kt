package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyGridItemScope
import androidx.tv.foundation.lazy.grid.TvLazyGridState
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import com.google.common.collect.Iterables

@Composable
fun <T: Any>CommonLazyVerticalGrid(
    modifier: Modifier = Modifier,
    state: TvLazyGridState,
    items: Iterable<T>,
    onBuildContent: @Composable TvLazyGridItemScope.(item: T) -> Unit
) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Adaptive(minSize = 150.dp),
        state = state,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(Iterables.size(items)) { index ->
            onBuildContent(Iterables.get(items, index))
        }
    }
}