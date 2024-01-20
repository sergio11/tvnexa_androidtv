package com.dreamsoftware.tvnexa.ui.navigation

sealed class Screens(val path: String) {

    data object Onboarding: Screens("onboarding")
    data object SignIn : Screens("sign_in")
    data object SignUp : Screens("sign_up")
    sealed class Home private constructor(path: String) : Screens(path) {
        companion object {
            val DEFAULT = Channels
        }

        data object Channels : Home("channels")
        data object Search : Home("search")
        data object Movies : Home("movies")
        data object Favorites : Home("favourites")
        data object Settings : Home("settings")
    }
    data object Player : Screens("player_screen")
    data object ProductDetail : Screens("product_detail")
    data object WhoIsWatching : Screens("who_is_watching")
}
