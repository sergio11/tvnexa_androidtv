@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.ChannelGridItem
import com.dreamsoftware.tvnexa.ui.components.ChannelPreview
import com.dreamsoftware.tvnexa.ui.components.CommonLazyVerticalGrid
import com.dreamsoftware.tvnexa.ui.components.CommonListItem
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import kotlinx.coroutines.delay

@Composable
fun ChannelScreenContent(
    uiState: ChannelsUiState,
    onNewCountrySelected: (CountryBO) -> Unit,
    onChannelFocused: (SimpleChannelBO) -> Unit
) {
    with(uiState) {
        with(MaterialTheme.colorScheme) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(surface),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CountryListColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.2f)
                        .background(primaryContainer.copy(alpha = 0.5f))
                        .border(1.dp, primary),
                    countryList = countries,
                    countrySelected = countrySelected,
                    onCountrySelected = onNewCountrySelected
                )
                ChannelsGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    channels = channels,
                    channelFocused = channelFocused,
                    onChannelFocused = onChannelFocused
                )
            }
        }
    }
}

@Composable
private fun ChannelsGrid(
    modifier: Modifier,
    channelFocused: SimpleChannelBO? = null,
    channels: List<SimpleChannelBO>,
    onChannelFocused: (SimpleChannelBO) -> Unit
) {
    val requester = remember { FocusRequester() }
    if(channels.isNotEmpty() && channelFocused != null) {
        LaunchedEffect(Unit) {
            delay(1000)
            requester.requestFocus()
        }
    }
    Column(
        modifier = modifier
    ) {
        channelFocused?.let {
            ChannelPreview(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(start = 10.dp),
                channel = it
            )
        }
        CommonLazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            state = rememberTvLazyGridState(),
            items = channels
        ) { item ->
            ChannelGridItem(
                modifier = if(item == channelFocused) Modifier.focusRequester(requester) else Modifier,
                channel = item,
                onChannelFocused = onChannelFocused
            )
        }
    }
}

@Composable
private fun CountryListColumn(
    modifier: Modifier,
    countryList: List<CountryBO>,
    countrySelected: CountryBO? = null,
    onCountrySelected: (CountryBO) -> Unit
) {
    val requester = remember { FocusRequester() }
    if(countryList.isNotEmpty() && countrySelected != null) {
        LaunchedEffect(Unit) {
            delay(1000)
            requester.requestFocus()
        }
    }
    Box(
        modifier = modifier,
    ) {
        TvLazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(countryList.size) { idx ->
                val currentCountry = countryList[idx]
                CommonListItem(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
                    .then(if (currentCountry == countrySelected) Modifier.focusRequester(requester) else Modifier),
                    onClicked = {
                        onCountrySelected(currentCountry)
                    }
                ) { isFocused ->
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CommonText(
                            type = CommonTextTypeEnum.TITLE_LARGE,
                            titleText = currentCountry.flag,
                            textAlign = TextAlign.Center
                        )
                        CommonText(
                            type = CommonTextTypeEnum.BODY_LARGE,
                            titleText = currentCountry.name,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            textColor = with(MaterialTheme.colorScheme) {
                                if(isFocused) {
                                    primary
                                } else {
                                    onPrimaryContainer
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

