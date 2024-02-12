package com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: String,
    @StringRes val title: Int,
    val icon: ImageVector? = null,
)