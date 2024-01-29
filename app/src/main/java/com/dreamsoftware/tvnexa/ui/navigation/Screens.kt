package com.dreamsoftware.tvnexa.ui.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dreamsoftware.tvnexa.ui.features.details.DetailScreenArgs

sealed class Screens(val path: String, arguments: List<NamedNavArgument> = emptyList()) {

    data object Splash: Screens("splash")
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
    data object Detail : Screens("detail/{channel_id}", arguments = listOf(
        navArgument("channel_id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(channelId: String): String =
            path.replace(
                oldValue = "{channel_id}",
                newValue = channelId
            )

        fun parseArgs(args: Bundle): DetailScreenArgs? = with(args) {
            getString("channel_id")?.let {
                DetailScreenArgs(channelId = it)
            }
        }
    }
    data object Profiles : Screens("profiles")
}
