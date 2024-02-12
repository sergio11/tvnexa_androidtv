package com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data

import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model.MenuItem
import com.dreamsoftware.tvnexa.ui.navigation.Screens
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.CogSolid
import compose.icons.lineawesomeicons.HeartSolid
import compose.icons.lineawesomeicons.HomeSolid
import compose.icons.lineawesomeicons.SearchSolid
import compose.icons.lineawesomeicons.TvSolid

object MenuData {

    val mainMenuItems = listOf(
        MenuItem(
            id = Screens.Home.DEFAULT.path,
            title = R.string.home_menu_main_item_text,
            icon = LineAwesomeIcons.HomeSolid
        ),
        MenuItem(
            id = Screens.Home.Search.path,
            title = R.string.home_menu_search_item_text,
            icon = LineAwesomeIcons.SearchSolid
        ),
        MenuItem(
            id = Screens.Home.Guide.path,
            title = R.string.home_menu_guide_item_text,
            icon = LineAwesomeIcons.TvSolid
        ),
        MenuItem(
            id = Screens.Home.Favorites.path,
            title = R.string.home_menu_favorites_item_text,
            icon = LineAwesomeIcons.HeartSolid
        )
    )

    val secondaryMenuItems = listOf(
        MenuItem(
            id = Screens.Home.Settings.DEFAULT.path,
            title = R.string.home_menu_settings_item_text,
            icon = LineAwesomeIcons.CogSolid
        )
    )
}
