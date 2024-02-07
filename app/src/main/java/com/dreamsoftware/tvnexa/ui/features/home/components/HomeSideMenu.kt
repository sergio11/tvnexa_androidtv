@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.rememberDrawerState
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.ui.components.CommonNavigationDrawerItem
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ScalableAvatar
import com.dreamsoftware.tvnexa.ui.extensions.toDrawableResource
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model.MenuItem
import com.dreamsoftware.tvnexa.ui.theme.Dimens
import com.dreamsoftware.tvnexa.ui.theme.Dimens.HOME_SIDE_MENU_AVATAR_FOCUSED_SCALE
import com.dreamsoftware.tvnexa.ui.theme.Dimens.HOME_SIDE_MENU_AVATAR_PADDING
import com.dreamsoftware.tvnexa.ui.theme.Dimens.HOME_SIDE_MENU_CLOSE_AVATAR_SIZE
import com.dreamsoftware.tvnexa.ui.theme.Dimens.HOME_SIDE_MENU_OPEN_AVATAR_SIZE
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.InfoSolid

@Composable
fun HomeSideMenu(
    mainMenuItems: List<MenuItem>,
    secondaryMenuItems: List<MenuItem>,
    menuItemIdSelected: String,
    profileSelected: ProfileBO? = null,
    onMenuItemSelected: (String) -> Unit = {},
    content: @Composable () -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(
                    Modifier
                        .background(surface)
                        .fillMaxHeight()
                        .padding(Dimens.HOME_SIDE_MENU_DRAWER_CONTENT_PADDING)
                        .selectableGroup(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = Dimens.HOME_SIDE_MENU_DRAWER_CONTENT_VERTICAL_ARRANGEMENT,
                        alignment = Alignment.CenterVertically
                    ),
                ) {
                    SideMenuLogo(drawerState = drawerState)
                    profileSelected?.let {
                        ProfileSelectedItem(drawerState = drawerState, profileSelected = it)
                    }
                    mainMenuItems.forEach { item ->
                        SideMenuItem(item = item,
                            isSelected = menuItemIdSelected == item.id,
                            onMenuItemSelected = {
                                drawerState.setValue(DrawerValue.Closed)
                                onMenuItemSelected.invoke(item.id)
                            })
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    secondaryMenuItems.forEach { item ->
                        SideMenuItem(item = item,
                            isSelected = menuItemIdSelected == item.id,
                            onMenuItemSelected = {
                                drawerState.setValue(DrawerValue.Closed)
                                onMenuItemSelected.invoke(item.id)
                            }
                        )
                    }
                }
            }, scrimBrush = Brush.horizontalGradient(
                listOf(surface, primary.copy(alpha = 0.3f))
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = Dimens.HOME_SIDE_MENU_CLOSE_DRAWER_WIDTH)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun ColumnScope.ProfileSelectedItem(drawerState: DrawerState, profileSelected: ProfileBO) {
    with(MaterialTheme.colorScheme) {
        with(drawerState) {
            with(profileSelected) {
                var hasFocus by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .onFocusChanged { hasFocus = it.hasFocus }
                        .clip(RoundedCornerShape(10.dp))
                        .background(if(hasFocus) primary.copy(0.5f) else onPrimary)
                        .padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        ScalableAvatar(
                            avatarRes = type.toDrawableResource(),
                            focusedScale = HOME_SIDE_MENU_AVATAR_FOCUSED_SCALE,
                            size = if(currentValue == DrawerValue.Open) {
                                HOME_SIDE_MENU_OPEN_AVATAR_SIZE
                            } else {
                                HOME_SIDE_MENU_CLOSE_AVATAR_SIZE
                            },
                            padding = HOME_SIDE_MENU_AVATAR_PADDING,
                            borderColor = primary
                        )
                        if(currentValue == DrawerValue.Open) {
                            CommonText(
                                modifier = Modifier.padding(start = 15.dp),
                                type = CommonTextTypeEnum.TITLE_MEDIUM,
                                titleText = alias,
                                textAlign = TextAlign.Start,
                                textBold = true,
                                textColor = if(hasFocus) {
                                    onPrimary
                                } else {
                                    primary
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun ColumnScope.SideMenuLogo(drawerState: DrawerState) {
    with(drawerState) {
        Spacer(modifier = Modifier.height(
            if(currentValue == DrawerValue.Closed) {
                0.dp
            } else {
                10.dp
            }
        ))
        Image(
            painter = painterResource(id = if(currentValue == DrawerValue.Open) {
                R.drawable.tvnexa_logo
            } else {
                R.drawable.tvnexa_logo_mini
            }),
            contentDescription = null,
            modifier = Modifier
                .height(
                    if (currentValue == DrawerValue.Open) {
                        Dimens.HOME_SIDE_MENU_OPEN_LOGO_HEIGHT
                    } else {
                        Dimens.HOME_SIDE_MENU_CLOSE_LOGO_HEIGHT
                    }
                )
                .align(
                    if (currentValue == DrawerValue.Open) {
                        Alignment.Start
                    } else {
                        Alignment.CenterHorizontally
                    }
                )
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun NavigationDrawerScope.SideMenuItem(
    item: MenuItem,
    isSelected: Boolean,
    enabled: Boolean = true,
    onMenuItemSelected: ((menuItem: MenuItem) -> Unit)?
) {
    with(MaterialTheme.colorScheme) {
        CommonNavigationDrawerItem(
            isSelected = isSelected,
            enabled = enabled,
            onPressed = { onMenuItemSelected?.invoke(item) },
            leadingContent = { isFocused ->
                Icon(
                    imageVector = item.icon ?: LineAwesomeIcons.InfoSolid,
                    contentDescription = item.text,
                    tint = if(isFocused || isSelected) {
                        onPrimary
                    } else {
                        primary
                    }
                )
            }
        ) { isFocused ->
            CommonText(
                titleText = item.text,
                type = CommonTextTypeEnum.TITLE_MEDIUM,
                textColor = if(isFocused || isSelected) {
                    onPrimary
                } else {
                    primary
                }
            )
        }
    }
}