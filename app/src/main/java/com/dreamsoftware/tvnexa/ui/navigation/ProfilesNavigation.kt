package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.profiles.save.SaveProfileScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.selector.ProfileSelectorScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.transitionComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilesNavigation(
    navController: NavHostController,
    onGoToHome: () -> Unit
) {
    AnimatedNavHost(navController = navController, startDestination = Screens.Profiles.DEFAULT.path) {

        transitionComposable(Screens.Profiles.DEFAULT.path) {
            with(navController) {
                ProfileSelectorScreen(
                    onProfileSelected = onGoToHome,
                    onGoToAddProfile = {
                        navigate(Screens.Profiles.AddProfile.path)
                    },
                    onGoToProfileManagement = {}
                )
            }
        }

        transitionComposable(Screens.Profiles.AddProfile.path) {
            SaveProfileScreen(
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        transitionComposable(Screens.Profiles.EditProfile.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Profiles.EditProfile.parseArgs(args)?.let {
                    SaveProfileScreen(
                        args = it,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                    )
                }
            }
        }
    }
}