package com.dreamsoftware.tvnexa.ui.features.settings.data

import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.features.settings.model.SettingsItemMenu
import com.dreamsoftware.tvnexa.ui.navigation.Screens

object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsItemMenu(
                id = Screens.Home.Settings.Profile.path,
                title = R.string.setting_menu_profile_item_text
            ),
            SettingsItemMenu(
                id = Screens.Home.Settings.AboutMe.path,
                title = R.string.setting_menu_about_me_item_text
            ),
        )
    }
}
