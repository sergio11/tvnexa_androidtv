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
    onGoToProfileSelector: () -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    val navController = rememberAnimatedNavController()
    CommonScreen(
        viewModel = homeViewModel,
        onInitialUiState = { HomeUiState() },
        onInit = {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                onMenuItemSelected(destination.route ?: return@addOnDestinationChangedListener)
            }
            load()
        }
    ) { uiState ->
        HomeScreenContent(
            uiState = uiState,
            navController = navController,
            onGoToProfileSelector = onGoToProfileSelector,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}
