package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.details.DetailsScreen
import com.dreamsoftware.tvnexa.ui.features.home.HomeScreen
import com.dreamsoftware.tvnexa.ui.features.onboarding.OnboardingScreen
import com.dreamsoftware.tvnexa.ui.features.player.PlayerScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.ProfilesScreen
import com.dreamsoftware.tvnexa.ui.features.signin.SignInScreen
import com.dreamsoftware.tvnexa.ui.features.signup.SignUpScreen
import com.dreamsoftware.tvnexa.ui.features.splash.SplashScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.navigateSingleTopTo
import com.dreamsoftware.tvnexa.ui.navigation.extensions.transitionComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(navController: NavHostController) {
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
                        navigateSingleTopTo(Screens.Profiles.DEFAULT.path)
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
                        navigateSingleTopTo(Screens.Profiles.DEFAULT.path)
                    },
                    onGoToSignUp = {
                        navigate(Screens.SignUp.path)
                    },
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }

        composable(Screens.SignUp.path) {
            with(navController) {
                SignUpScreen(
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }

        composable(Screens.Profiles.DEFAULT.path) {
            ProfilesScreen {
                navController.navigateSingleTopTo(Screens.Home.DEFAULT.path)
            }
        }

        composable(Screens.Home.DEFAULT.path) {
            with(navController) {
                HomeScreen(
                    onGoToProfileSelector = {
                        navigate(Screens.Profiles.DEFAULT.path)
                    },
                    onNavigateToDetail = { channelId ->
                        navigate(Screens.Detail.buildRoute(channelId))
                    }
                )
            }
        }

        transitionComposable(Screens.Player.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Player.parseArgs(args)?.let {
                    PlayerScreen(
                        args = it,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                    )
                }
            }
        }

        transitionComposable(Screens.Detail.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Detail.parseArgs(args)?.let {
                    DetailsScreen(
                        args = it,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                        onPlayChannelPressed = { channelId ->
                            navController.navigate(Screens.Player.buildRoute(channelId))
                        },
                    )
                }
            }
        }
    }
}


