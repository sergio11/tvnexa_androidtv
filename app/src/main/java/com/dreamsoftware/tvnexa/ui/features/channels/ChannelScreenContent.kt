@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.channels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.ChannelPreview
import com.dreamsoftware.tvnexa.ui.components.CommonLazyVerticalGrid
import com.dreamsoftware.tvnexa.ui.components.CommonListItem
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum

@Composable
fun ChannelScreenContent(
    uiState: ChannelsUiState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountryListColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.2f)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
            countryList = uiState.countries,
            onCountrySelected = {}
        )
        ChannelsGrid(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            channels = uiState.channels
        )
    }
}

@Composable
private fun ChannelsGrid(
    modifier: Modifier,
    channels: List<SimpleChannelBO>
) {
    Column(
        modifier = modifier
    ) {
        ChannelPreview(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(start = 10.dp)
        )
        CommonLazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            state = rememberTvLazyGridState(),
            items = channels
        ) { item ->
            CommonListItem(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
                onClicked = {  }
            ) { isFocused ->
                CommonText(
                    type = CommonTextTypeEnum.TITLE_SMALL,
                    titleText = item.name,
                    textAlign = TextAlign.Center,
                    textColor = with(MaterialTheme.colorScheme) {
                        onPrimaryContainer
                    }
                )
            }
        }
    }
}

@Composable
private fun CountryListColumn(
    modifier: Modifier,
    countryList: List<CountryBO>,
    onCountrySelected: (CountryBO) -> Unit
) {
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
                    .padding(8.dp),
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

