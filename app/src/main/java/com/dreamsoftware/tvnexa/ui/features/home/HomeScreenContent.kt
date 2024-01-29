package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.home.components.HomeSideMenu
import com.dreamsoftware.tvnexa.ui.navigation.HomeNavigation

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    onNavigateToDetail: (String) -> Unit,
    selectedId: String
) {
    HomeSideMenu(
        content = { HomeNavigation(
            navController = navController,
            onNavigateToDetail = onNavigateToDetail
        ) },
        selectedId = selectedId
    ) { navController.navigate(it.id) }
}
