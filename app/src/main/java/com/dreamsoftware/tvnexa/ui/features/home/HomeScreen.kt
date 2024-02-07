package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.tvnexa.ui.components.CommonScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit
) {
    val navController = rememberAnimatedNavController()
    CommonScreen(
        viewModel = homeViewModel,
        onInitialUiState = { HomeUiState() },
        onInit = {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                homeViewModel.onMenuItemSelected(destination.route ?: return@addOnDestinationChangedListener)
            }
        }
    ) { uiState ->
        HomeScreenContent(
            uiState = uiState,
            navController = navController,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}
