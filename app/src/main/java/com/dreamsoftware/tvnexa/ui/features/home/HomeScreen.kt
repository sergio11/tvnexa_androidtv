package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onItemFocus: (parent: Int, child: Int) -> Unit
) {
    with(homeViewModel) {
        HomeScreenContent(
            onItemFocus = onItemFocus
        )
    }
}
