package com.dreamsoftware.tvnexa.ui.features.home.settings.data

import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.features.home.settings.model.SettingsItemMenu
import com.dreamsoftware.tvnexa.ui.navigation.Screens

object SettingsMenuData {
    val menu by lazy {
        listOf(
            SettingsItemMenu(
                id = Screens.Home.Settings.Account.path,
                title = R.string.setting_menu_account_item_text
            ),
            SettingsItemMenu(
                id = Screens.Home.Settings.AboutMe.path,
                title = R.string.setting_menu_about_me_item_text
            ),
            SettingsItemMenu(
                id = Screens.Home.Settings.Help.path,
                title = R.string.setting_menu_help_item_text
            ),
            SettingsItemMenu(
                id = Screens.Home.Settings.TermsAndConditions.path,
                title = R.string.setting_menu_terms_and_conditions_item_text
            ),
            SettingsItemMenu(
                id = Screens.Home.Settings.Info.path,
                title = R.string.setting_menu_info_item_text
            )
        )
    }
}
