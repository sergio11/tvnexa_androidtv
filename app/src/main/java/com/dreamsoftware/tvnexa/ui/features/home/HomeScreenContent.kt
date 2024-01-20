package com.dreamsoftware.tvnexa.ui.features.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data.MenuData
import com.dreamsoftware.tvnexa.ui.features.home.navigation.NestedHomeNavigation
import com.dreamsoftware.tvnexa.ui.features.home.navigation.drawer.HomeSideMenu
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreenContent(
    onItemFocus: (parent: Int, child: Int) -> Unit
) {
    val navController = rememberAnimatedNavController()

    val selectedId = remember {
        mutableStateOf(MenuData.menuItems.first().id)
    }

    LaunchedEffect(key1 = Unit) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedId.value = destination.route ?: return@addOnDestinationChangedListener
        }
    }

    HomeSideMenu(content = {
        NestedHomeNavigation(navController, onItemFocus)
    }, selectedId = selectedId.value) {
        navController.navigate(it.id)
    }
}



@Preview
@Composable
fun HomeScreenContentPrev() {
    TvNexaTheme {
        HomeScreenContent(onItemFocus = { _, _ -> })
    }
}
