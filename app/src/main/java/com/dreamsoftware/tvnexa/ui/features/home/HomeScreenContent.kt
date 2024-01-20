package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.home.components.HomeSideMenu
import com.dreamsoftware.tvnexa.ui.navigation.HomeNavigation

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    selectedId: String,
    onItemFocus: (parent: Int, child: Int) -> Unit
) {
    HomeSideMenu(
        content = { HomeNavigation(navController, onItemFocus) },
        selectedId = selectedId
    ) { navController.navigate(it.id) }
}
