package com.dreamsoftware.tvnexa.features.settings

import com.dreamsoftware.tvnexa.features.settings.data.SettingsMenuModel
import com.dreamsoftware.tvnexa.features.settings.navigation.SettingsScreens

object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsMenuModel("Profile", SettingsScreens.Profile.title),
            SettingsMenuModel("About Me", SettingsScreens.AboutMe.title),
        )
    }
}
