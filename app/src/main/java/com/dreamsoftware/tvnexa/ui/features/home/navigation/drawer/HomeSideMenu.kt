@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.navigation.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data.MenuData
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model.MenuItem
import com.dreamsoftware.tvnexa.ui.theme.Dimens

@Composable
fun HomeSideMenu(
    content: @Composable () -> Unit,
    selectedId: String = MenuData.menuItems.first().id,
    onMenuItemSelected: ((menuItem: MenuItem) -> Unit)?
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
                    MenuData.menuItems.forEachIndexed { index, item ->
                        SideMenuItem(item = item,
                            isSelected = selectedId == item.id,
                            onMenuItemSelected = {
                                drawerState.setValue(DrawerValue.Closed)
                                onMenuItemSelected?.invoke(item)
                            })
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    SideMenuItem(item = MenuData.settingsItem,
                        isSelected = selectedId == MenuData.settingsItem.id,
                        onMenuItemSelected = {
                            drawerState.setValue(DrawerValue.Closed)
                            onMenuItemSelected?.invoke(MenuData.settingsItem)
                        })
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
private fun ColumnScope.SideMenuLogo(drawerState: DrawerState) {
    with(drawerState) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = if(currentValue == DrawerValue.Open) {
                R.drawable.tvnexa_logo
            } else {
                R.drawable.tvnexa_logo_mini
            }),
            contentDescription = null,
            modifier = Modifier
                .height(if(currentValue == DrawerValue.Open) {
                    Dimens.HOME_SIDE_MENU_OPEN_LOGO_HEIGHT
                } else {
                    Dimens.HOME_SIDE_MENU_CLOSE_LOGO_HEIGHT
                }).align(
                    if(currentValue == DrawerValue.Open) {
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
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    with(MaterialTheme.colorScheme) {
        NavigationDrawerItem(
            selected = isSelected,
            enabled = enabled,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = primary.copy(alpha = 0.5f),
                focusedContainerColor = primary.copy(alpha = 0.5f),
            ),
            onClick = {
                onMenuItemSelected?.invoke(item)
            },
            leadingContent = {
                Icon(
                    imageVector = item.icon ?: return@NavigationDrawerItem,
                    contentDescription = item.text,
                    tint = if(isFocused) {
                        onPrimary
                    } else {
                        primary
                    }
                )
            },
            interactionSource = interactionSource
        ) {
            CommonText(
                titleText = item.text,
                type = CommonTextTypeEnum.TITLE_MEDIUM,
                textColor = if(isFocused) {
                    onPrimary
                } else {
                    primary
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeDrawerPrev() {
    HomeSideMenu(content = {
        Text(text = "Hello World")
    }, onMenuItemSelected = null)
}
