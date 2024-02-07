package com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data

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
        MenuItem(Screens.Home.DEFAULT.path, "Home", LineAwesomeIcons.HomeSolid),
        MenuItem(Screens.Home.Search.path, "Search", LineAwesomeIcons.SearchSolid),
        MenuItem(Screens.Home.Guide.path, "Guide", LineAwesomeIcons.TvSolid),
        MenuItem(Screens.Home.Favorites.path, "Favorites", LineAwesomeIcons.HeartSolid),
    )

    val secondaryMenuItems = listOf(
        MenuItem(Screens.Home.Settings.path, "Settings", LineAwesomeIcons.CogSolid,)
    )

}
