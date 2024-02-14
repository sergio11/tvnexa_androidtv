package com.dreamsoftware.tvnexa.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.about.AboutScreen
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.help.HelpScreen
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.info.InfoScreen
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.account.AccountScreen
import com.dreamsoftware.tvnexa.ui.features.home.settings.screens.termsandconditions.TermsAndConditionsScreen
import com.dreamsoftware.tvnexa.ui.navigation.extensions.transitionComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SettingsNavigation(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.Home.Settings.DEFAULT.path,
    ) {
        transitionComposable(Screens.Home.Settings.Account.path) {
            AccountScreen()
        }
        transitionComposable(Screens.Home.Settings.AboutMe.path) {
            AboutScreen()
        }
        transitionComposable(Screens.Home.Settings.Help.path) {
            HelpScreen()
        }
        transitionComposable(Screens.Home.Settings.TermsAndConditions.path) {
            TermsAndConditionsScreen()
        }
        transitionComposable(Screens.Home.Settings.Info.path) {
            InfoScreen()
        }
    }
}
