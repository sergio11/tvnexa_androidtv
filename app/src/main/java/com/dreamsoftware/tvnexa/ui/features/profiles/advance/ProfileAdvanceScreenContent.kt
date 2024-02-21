@file:OptIn(ExperimentalTvMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.dreamsoftware.tvnexa.ui.features.profiles.advance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabDefaults
import androidx.tv.material3.TabRow
import androidx.tv.material3.TabRowDefaults
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.MiniKeyboard
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent

@Composable
fun ProfileAdvanceScreenContent(
    uiState: ProfileAdvanceUiState,
    onConfirmPressed: () -> Unit,
    onDeleteProfilePressed: () -> Unit,
    onNewTabSelected: (ProfileAdvancedTab) -> Unit,
    onKeyPressed: (String) -> Unit,
    onSearchPressed: () -> Unit,
    onClearPressed: () -> Unit,
    onBackSpacePressed: () -> Unit,
    onSpaceBarPressed: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            mainTitleRes = R.string.profiles_advance_main_title,
            secondaryTitleRes = R.string.profiles_advance_main_description,
            primaryOptionTextRes = R.string.profiles_advance_form_confirm_button_text,
            secondaryOptionTextRes = R.string.profiles_advance_form_delete_button_text,
            onPrimaryOptionPressed = onConfirmPressed,
            onSecondaryOptionPressed = onDeleteProfilePressed
        ) {
            ProfileAdvanceTabs(
                tabs = tabs,
                tabSelected = tabSelected,
                onTabSelected = onNewTabSelected
            )
            when(tabSelected) {
                ProfileAdvancedTab.ChannelBlockingTab -> ProfileAdvanceChannelBlocking(
                    onKeyPressed = onKeyPressed,
                    onSearchPressed = onSearchPressed,
                    onClearPressed = onClearPressed,
                    onBackSpacePressed = onBackSpacePressed,
                    onSpaceBarPressed = onSpaceBarPressed
                )
                ProfileAdvancedTab.TimeRestrictionsTab -> ProfileAdvanceTimeRestrictions()
            }
        }
    }
}

@Composable
private fun ProfileAdvanceTabs(
    tabs: List<ProfileAdvancedTab>,
    tabSelected: ProfileAdvancedTab,
    onTabSelected: (ProfileAdvancedTab) -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val selectedTabIndex = tabs.indexOfFirst { it == tabSelected }.takeIf { it >= 0 } ?: 0
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions, doesTabRowHaveFocus ->
                TabRowDefaults.PillIndicator(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    doesTabRowHaveFocus = doesTabRowHaveFocus,
                    activeColor = primary,
                    inactiveColor = secondary
                )
            },
            modifier = Modifier.focusRestorer()
        ) {
            tabs.forEachIndexed { index, tab ->
                key(index) {
                    Tab(
                        selected = index == selectedTabIndex,
                        onFocus = { onTabSelected(tab) },
                        colors = TabDefaults.underlinedIndicatorTabColors(),
                    ) {
                        CommonText(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                            type = CommonTextTypeEnum.LABEL_LARGE,
                            titleRes = tab.titleRes
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileAdvanceChannelBlocking(
    onKeyPressed: (String) -> Unit,
    onSearchPressed: () -> Unit,
    onClearPressed: () -> Unit,
    onBackSpacePressed: () -> Unit,
    onSpaceBarPressed: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 30.dp)
    ) {
        Column {
            MiniKeyboard(
                modifier = Modifier.width(300.dp),
                onKeyPressed = onKeyPressed,
                onSearchPressed = onSearchPressed,
                onClearPressed = onClearPressed,
                onBackSpacePressed = onBackSpacePressed,
                onSpaceBarPressed = onSpaceBarPressed
            )
        }
    }
}

@Composable
private fun ProfileAdvanceTimeRestrictions() {

}