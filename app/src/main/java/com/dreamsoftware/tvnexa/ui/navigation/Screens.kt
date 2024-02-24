package com.dreamsoftware.tvnexa.ui.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dreamsoftware.tvnexa.ui.features.details.DetailScreenArgs
import com.dreamsoftware.tvnexa.ui.features.player.PlayerScreenArgs
import com.dreamsoftware.tvnexa.ui.features.profiles.advance.ProfileAdvanceScreenArgs
import com.dreamsoftware.tvnexa.ui.features.profiles.blocking.ProfileBlockingChannelsScreenArgs
import com.dreamsoftware.tvnexa.ui.features.profiles.delete.DeleteProfileScreenArgs
import com.dreamsoftware.tvnexa.ui.features.profiles.save.SaveProfileScreenArgs
import com.dreamsoftware.tvnexa.ui.features.profiles.secure.SecurePinScreenArgs

sealed class Screens(val path: String, arguments: List<NamedNavArgument> = emptyList()) {

    data object Splash: Screens("splash")
    data object Onboarding: Screens("onboarding")
    data object SignIn : Screens("sign_in")
    data object SignUp : Screens("sign_up")

    sealed class Profiles private constructor(path: String) : Screens(path) {
        companion object {
            val DEFAULT = Selector
        }
        data object Selector : Profiles("profile_selector")

        data object AddProfile : Profiles("add_profile")

        data object Management : Profiles("profiles_management")

        data object UnlockProfile : Screens("unlock_profile/{profile_id}", arguments = listOf(
            navArgument("profile_id") {
                type = NavType.StringType
            }
        )) {
            fun buildRoute(profileId: String): String =
                path.replace(
                    oldValue = "{profile_id}",
                    newValue = profileId
                )

            fun parseArgs(args: Bundle): SecurePinScreenArgs? = with(args) {
                getString("profile_id")?.let {
                    SecurePinScreenArgs(profileId = it)
                }
            }
        }

        data object DeleteProfile : Screens("delete_profile/{profile_id}", arguments = listOf(
            navArgument("profile_id") {
                type = NavType.StringType
            }
        )) {
            fun buildRoute(profileId: String): String =
                path.replace(
                    oldValue = "{profile_id}",
                    newValue = profileId
                )

            fun parseArgs(args: Bundle): DeleteProfileScreenArgs? = with(args) {
                getString("profile_id")?.let {
                    DeleteProfileScreenArgs(profileId = it)
                }
            }
        }

        data object EditProfile : Screens("edit_profile/{profile_id}", arguments = listOf(
            navArgument("profile_id") {
                type = NavType.StringType
            }
        )) {
            fun buildRoute(profileId: String): String =
                path.replace(
                    oldValue = "{profile_id}",
                    newValue = profileId
                )

            fun parseArgs(args: Bundle): SaveProfileScreenArgs? = with(args) {
                getString("profile_id")?.let {
                    SaveProfileScreenArgs(profileId = it)
                }
            }
        }

        data object ProfileAdvance : Screens("advance/{profile_id}", arguments = listOf(
            navArgument("profile_id") {
                type = NavType.StringType
            }
        )) {
            fun buildRoute(profileId: String): String =
                path.replace(
                    oldValue = "{profile_id}",
                    newValue = profileId
                )

            fun parseArgs(args: Bundle): ProfileAdvanceScreenArgs? = with(args) {
                getString("profile_id")?.let {
                    ProfileAdvanceScreenArgs(profileId = it)
                }
            }
        }

        data object ProfileBlockingChannels : Screens("blocking-channels/{profile_id}", arguments = listOf(
            navArgument("profile_id") {
                type = NavType.StringType
            }
        )) {
            fun buildRoute(profileId: String): String =
                path.replace(
                    oldValue = "{profile_id}",
                    newValue = profileId
                )

            fun parseArgs(args: Bundle): ProfileBlockingChannelsScreenArgs? = with(args) {
                getString("profile_id")?.let {
                    ProfileBlockingChannelsScreenArgs(profileId = it)
                }
            }
        }
    }
    sealed class Home private constructor(path: String) : Screens(path) {
        companion object {
            val DEFAULT = Channels
        }

        data object Channels : Home("channels")
        data object Search : Home("search")
        data object Guide : Home("guide")
        data object Favorites : Home("favorites")
        sealed class Settings private constructor(path: String): Home(path) {
            companion object {
                val DEFAULT = Account
            }
            data object Account : Settings("settings/account")
            data object AboutMe : Settings("settings/about_me")
            data object Help : Settings("settings/help")
            data object TermsAndConditions: Settings("settings/terms_and_conditions")
            data object Info: Settings("settings/info")
        }
    }
    data object Player : Screens("player_screen/{channel_id}", arguments = listOf(
        navArgument("channel_id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(channelId: String): String =
            path.replace(
                oldValue = "{channel_id}",
                newValue = channelId
            )

        fun parseArgs(args: Bundle): PlayerScreenArgs? = with(args) {
            getString("channel_id")?.let {
                PlayerScreenArgs(channelId = it)
            }
        }
    }

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
}
