package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.profiles.advance.ProfileAdvanceScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.blocking.ProfileBlockingChannelsScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.delete.DeleteProfileScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.management.ProfilesManagementScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.save.SaveProfileScreen
import com.dreamsoftware.tvnexa.ui.features.profiles.secure.SecurePinScreen
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
                    onProfileLocked = {
                        navigate(Screens.Profiles.UnlockProfile.buildRoute(it))
                    },
                    onGoToAddProfile = {
                        navigate(Screens.Profiles.AddProfile.path)
                    },
                    onGoToProfileManagement = {
                        navigate(Screens.Profiles.Management.path)
                    }
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
                    with(navController) {
                        SaveProfileScreen(
                            args = it,
                            onGoToAdvanceConfiguration = {
                                navigate(Screens.Profiles.ProfileAdvance.buildRoute(it))
                            },
                            onBackPressed = {
                                navigateUp()
                            },
                        )
                    }
                }
            }
        }


        transitionComposable(Screens.Profiles.UnlockProfile.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Profiles.UnlockProfile.parseArgs(args)?.let {
                    SecurePinScreen(
                        args = it,
                        onGoToHome = onGoToHome,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                    )
                }
            }
        }

        transitionComposable(Screens.Profiles.Management.path) {
            with(navController) {
                ProfilesManagementScreen(
                    onGoToEditProfile = {
                        navigate(Screens.Profiles.EditProfile.buildRoute(it))
                    },
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }

        transitionComposable(Screens.Profiles.DeleteProfile.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Profiles.DeleteProfile.parseArgs(args)?.let {
                    DeleteProfileScreen(
                        args = it,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                    )
                }
            }
        }

        transitionComposable(Screens.Profiles.ProfileAdvance.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Profiles.ProfileAdvance.parseArgs(args)?.let {
                    with(navController) {
                        ProfileAdvanceScreen(
                            args = it,
                            onGoToDeleteProfile = {
                                navigate(Screens.Profiles.DeleteProfile.buildRoute(it))
                            },
                            onGoToBlockingChannels = {
                                navigate(Screens.Profiles.ProfileBlockingChannels.buildRoute(it))
                            },
                            onBackPressed = {
                                navigateUp()
                            },
                        )
                    }
                }
            }
        }


        transitionComposable(Screens.Profiles.ProfileBlockingChannels.path) { navBackStackEntry ->
            navBackStackEntry.arguments?.let { args ->
                Screens.Profiles.ProfileBlockingChannels.parseArgs(args)?.let {
                    with(navController) {
                        ProfileBlockingChannelsScreen(
                            args = it,
                            onBackPressed = {
                                navigateUp()
                            },
                        )
                    }
                }
            }
        }
    }
}
