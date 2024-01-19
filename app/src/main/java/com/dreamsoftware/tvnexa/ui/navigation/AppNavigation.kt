package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.details.DetailsScreen
import com.dreamsoftware.tvnexa.ui.features.home.HomeScreen
import com.dreamsoftware.tvnexa.ui.features.home.HomeViewModel
import com.dreamsoftware.tvnexa.ui.features.onboarding.OnboardingScreen
import com.dreamsoftware.tvnexa.ui.features.signin.SignInScreen
import com.dreamsoftware.tvnexa.ui.features.player.PlayerScreen
import com.dreamsoftware.tvnexa.ui.features.signup.SignUpScreen
import com.dreamsoftware.tvnexa.ui.features.wiw.WhoIsWatchingScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.navigateSingleTopTo
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, homeViewModel: HomeViewModel) {
    AnimatedNavHost(navController = navController, startDestination = Screens.Onboarding.path) {

        composable(Screens.Onboarding.path) {
            with(navController) {
                OnboardingScreen(
                    onGoToSignIn = {
                        navigate(Screens.SignIn.path)
                    },
                    onGoToSignUp = {
                        navigate(Screens.SignUp.path)
                    }
                )
            }
        }

        composable(Screens.SignIn.path) {
            with(navController) {
                SignInScreen(
                    onGoToHome = {
                        navigateSingleTopTo(Screens.WhoIsWatching.path)
                    },
                    onGoToSignUp = {
                        navigateSingleTopTo(Screens.SignUp.path)
                    }
                )
            }
        }

        composable(Screens.SignUp.path) {
            with(navController) {
                SignUpScreen(
                    onGoToHomeScreen = {

                    },
                    onBack = {
                        popBackStack()
                    }
                )
            }
        }

        composable(Screens.WhoIsWatching.path) {
            WhoIsWatchingScreen {
                navController.navigateSingleTopTo(Screens.Home.path)
            }
        }

        composable(Screens.Home.path) {
            HomeScreen(homeViewModel) { _, _ ->
                navController.navigate(Screens.ProductDetail.path)
            }
        }

        composable(
            Screens.Player.path,
        ) {
            PlayerScreen(
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                onBackPressed = {
                    navController.navigateUp()
                },
            )
        }

        composable(
            Screens.ProductDetail.path,
        ) {
            DetailsScreen(
                onBackPressed = {
                    navController.navigateUp()
                },
                onPlayClick = {
                    navController.navigate(Screens.Player.path)
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
