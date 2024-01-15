package com.dreamsoftware.tvnexa.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.features.details.ProductDetailsScreen
import com.dreamsoftware.tvnexa.features.home.HomeScreen
import com.dreamsoftware.tvnexa.features.home.HomeViewModel
import com.dreamsoftware.tvnexa.features.login.withEmailPassword.LoginScreen
import com.dreamsoftware.tvnexa.features.login.withToken.DeviceTokenAuthenticationScreen
import com.dreamsoftware.tvnexa.features.player.PlayerScreen
import com.dreamsoftware.tvnexa.features.wiw.WhoIsWatchingScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, homeViewModel: HomeViewModel) {
    AnimatedNavHost(navController = navController, startDestination = Screens.LoginToken.title) {
        // e.g will add auth routes here if when we will extend project
        composable(
            Screens.Login.title,
        ) {
            LoginScreen {
                navController.navigateSingleTopTo(Screens.WhoIsWatching.title)
            }
        }

        composable(
            Screens.LoginToken.title,
        ) {
            DeviceTokenAuthenticationScreen(onSkip = {
                navController.navigateSingleTopTo(Screens.Home.title)
            }) {
                navController.navigateSingleTopTo(it.title)
            }
        }

        composable(
            Screens.WhoIsWatching.title,
        ) {
            WhoIsWatchingScreen {
                navController.navigateSingleTopTo(Screens.Home.title)
            }
        }

        composable(
            Screens.Home.title,) {
            HomeScreen(homeViewModel, { _, _ ->
                navController.navigate(Screens.ProductDetail.title)
            }) {}
        }

        composable(
            Screens.Player.title,
        ) {
            PlayerScreen(
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                onBackPressed = {
                    navController.navigateUp()
                },
            )
        }

        composable(
            Screens.ProductDetail.title,
        ) {
            ProductDetailsScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                onPlayClick = {
                    navController.navigate(Screens.Player.title)
                },
            )
        }
    }
}

fun tabExitTransition(
    duration: Int = 500,
) = fadeOut(tween(duration / 2, easing = LinearEasing))

fun tabEnterTransition(
    duration: Int = 500,
    delay: Int = duration - 350,
) = fadeIn(tween(duration, duration - delay))

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id,
        ) {
            saveState = true
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
