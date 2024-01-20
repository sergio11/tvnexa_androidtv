package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data.MenuData
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onItemFocus: (parent: Int, child: Int) -> Unit
) {
    val navController = rememberAnimatedNavController()
    var selectedId by remember { mutableStateOf(MenuData.menuItems.first().id) }
    LaunchedEffect(key1 = Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedId = destination.route ?: return@addOnDestinationChangedListener
        }
    }
    with(homeViewModel) {
        HomeScreenContent(
            navController = navController,
            selectedId = selectedId,
            onItemFocus = onItemFocus
        )
    }
}
