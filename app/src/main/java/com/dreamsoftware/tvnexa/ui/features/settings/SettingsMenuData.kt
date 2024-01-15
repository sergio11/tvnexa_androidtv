package com.dreamsoftware.tvnexa.ui.features.settings

import com.dreamsoftware.tvnexa.ui.features.settings.data.SettingsMenuModel
import com.dreamsoftware.tvnexa.ui.features.settings.navigation.SettingsScreens

object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsMenuModel("Profile", SettingsScreens.Profile.title),
            SettingsMenuModel("About Me", SettingsScreens.AboutMe.title),
        )
    }
}
