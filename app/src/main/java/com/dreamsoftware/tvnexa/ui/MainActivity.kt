package com.dreamsoftware.tvnexa.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import com.dreamsoftware.tvnexa.ui.navigation.MainNavigation
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            TvApp(navController = rememberAnimatedNavController())
        }
    }

    @Composable
    fun TvApp(navController: NavHostController) {
        TvNexaTheme {
            MainNavigation(navController)
        }
    }
}
