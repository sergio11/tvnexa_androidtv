package com.dreamsoftware.tvnexa.ui.navigation

sealed class Screens(val path: String) {

    data object Onboarding: Screens("onboarding")
    data object SignIn : Screens("sign_in")
    data object SignUp : Screens("sign_up")
    data object Home : Screens("home_screen")
    data object Player : Screens("player_screen")
    data object ProductDetail : Screens("product_detail")
    data object WhoIsWatching : Screens("who_is_watching")
}
