package com.dreamsoftware.tvnexa.ui.features.settings.data

import com.dreamsoftware.tvnexa.ui.features.settings.model.SettingsItemMenu
import com.dreamsoftware.tvnexa.ui.navigation.Screens

object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsItemMenu(id = Screens.Home.Settings.Profile.path, title = "Profile"),
            SettingsItemMenu(id = Screens.Home.Settings.AboutMe.path, title = "About Me"),
        )
    }
}
