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
import com.dreamsoftware.tvnexa.ui.features.splash.SplashScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.ProfileSelectorScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.navigateSingleTopTo
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(navController: NavHostController, homeViewModel: HomeViewModel) {
    AnimatedNavHost(navController = navController, startDestination = Screens.Splash.path) {

        composable(Screens.Splash.path) {
            with(navController) {
                SplashScreen(
                    onGoToHome = {
                        navigateSingleTopTo(Screens.Home.DEFAULT.path)
                    },
                    onGoToOnboarding = {
                        navigateSingleTopTo(Screens.Onboarding.path)
                    },
                    onGoToProfileSelector = {
                        navigateSingleTopTo(Screens.Profiles.path)
                    }
                )
            }
        }

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
                        navigateSingleTopTo(Screens.Home.DEFAULT.path)
                    },
                    onGoToProfileSelector = {
                        navigateSingleTopTo(Screens.Profiles.path)
                    },
                    onGoToSignUp = {
                        navigate(Screens.SignUp.path)
                    }
                )
            }
        }

        composable(Screens.SignUp.path) {
            with(navController) {
                SignUpScreen(
                    onBack = {
                        popBackStack()
                    }
                )
            }
        }

        composable(Screens.Profiles.path) {
            ProfileSelectorScreen {
                navController.navigateSingleTopTo(Screens.Home.DEFAULT.path)
            }
        }

        composable(Screens.Home.DEFAULT.path) {
            HomeScreen(onNavigateToDetail = { channelId ->
                navController.navigate(Screens.Detail.buildRoute(channelId))
            })
        }

        composable(
            Screens.Player.path,
        ) {
            PlayerScreen(
                "https://streaming101tv.es/hls/websevilla.m3u8",
                onBackPressed = {
                    navController.navigateUp()
                },
            )
        }

        composable(
            Screens.Detail.path,
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Detail.parseArgs(args)?.let {
                    DetailsScreen(
                        args = it,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                        onPlayChannelPressed = {
                            navController.navigate(Screens.Player.path)
                        },
                    )
                }
            }
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
