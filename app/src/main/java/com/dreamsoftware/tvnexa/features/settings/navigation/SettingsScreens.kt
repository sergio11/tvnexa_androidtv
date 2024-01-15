package com.dreamsoftware.tvnexa.features.settings.navigation

sealed class SettingsScreens(val title: String) {
    object Profile : SettingsScreens("profile")
    object AboutMe : SettingsScreens("about_me")
}
