package com.dreamsoftware.tvnexa.ui.features.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun NestedHomeNavigation(
    navController: NavHostController,
    onItemFocus: (parent: Int, child: Int) -> Unit
) {
    NestedHomeScreenNavigation(navController, onItemFocus)
}
