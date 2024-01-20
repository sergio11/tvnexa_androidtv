package com.dreamsoftware.tvnexa.ui.features.home

import androidx.lifecycle.ViewModel
import com.dreamsoftware.tvnexa.ui.extensions.toMutable
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.data.MenuData
import com.dreamsoftware.tvnexa.ui.features.home.leftmenu.model.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val menuItems: StateFlow<List<MenuItem>> = MutableStateFlow(emptyList())
    val menuState: StateFlow<Boolean> = MutableStateFlow(false)

    init {
        menuItems.toMutable().value = MenuData.menuItems
    }

    fun menuClosed() {
        menuState.toMutable().value = false
    }

    fun menuOpen() {
        menuState.toMutable().value = true
    }
}
