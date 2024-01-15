package com.dreamsoftware.tvnexa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.dreamsoftware.tvnexa.ui.navigation.AppNavigation
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme
import com.dreamsoftware.tvnexa.ui.components.CustomDialog
import com.dreamsoftware.tvnexa.ui.extensions.registerOnBackPress
import com.dreamsoftware.tvnexa.ui.features.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val displayDialog = remember {
                mutableStateOf(false)
            }
            val homeViewModel: HomeViewModel by viewModels()
            App(navController = rememberAnimatedNavController(), homeViewModel = homeViewModel)

            registerOnBackPress {
                displayDialog.value = true
            }

            if (displayDialog.value) {
                CustomDialog(openDialogCustom = displayDialog) {
                    finish()
                }
            }
        }
    }

    @Composable
    fun App(navController: NavHostController, homeViewModel: HomeViewModel) {
        TvNexaTheme {
            AppNavigation(navController, homeViewModel)
        }
    }
}
