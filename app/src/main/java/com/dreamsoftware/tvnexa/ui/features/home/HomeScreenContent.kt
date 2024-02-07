package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.home.components.HomeSideMenu
import com.dreamsoftware.tvnexa.ui.navigation.HomeNavigation

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    navController: NavHostController,
    onNavigateToDetail: (String) -> Unit,
) {
    with(uiState) {
        HomeSideMenu(
            menuItemIdSelected = menuItemIdSelected,
            mainMenuItems = mainMenuItems,
            secondaryMenuItems = secondaryMenuItems,
            onMenuItemSelected = { navController.navigate(it) }
        ) {
            HomeNavigation(
                navController = navController,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}
