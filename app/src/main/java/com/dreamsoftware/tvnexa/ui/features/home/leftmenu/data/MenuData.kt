package com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data

import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model.MenuItem
import com.dreamsoftware.tvnexa.ui.features.home.navigation.NestedScreens
import com.dreamsoftware.tvnexa.ui.navigation.Screens
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.CogSolid
import compose.icons.lineawesomeicons.HeartSolid
import compose.icons.lineawesomeicons.HomeSolid
import compose.icons.lineawesomeicons.SearchSolid
import compose.icons.lineawesomeicons.VideoSolid

object MenuData {
    val menuItems = listOf(
        MenuItem(Screens.Home.path, "Home", LineAwesomeIcons.HomeSolid),
        MenuItem(NestedScreens.Search.title, "Search", LineAwesomeIcons.SearchSolid),
        MenuItem(NestedScreens.Movies.title, "Movies", LineAwesomeIcons.VideoSolid),
        MenuItem(NestedScreens.Favorites.title, "Favorites", LineAwesomeIcons.HeartSolid),
    )

    val settingsItem = MenuItem(
        NestedScreens.Settings.title,
        "Settings",
        LineAwesomeIcons.CogSolid,
    )
}
