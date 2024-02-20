@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.channels

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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.ui.components.ChannelGridItem
import com.dreamsoftware.tvnexa.ui.components.ChannelPreview
import com.dreamsoftware.tvnexa.ui.components.CommonChip
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonLazyVerticalGrid
import com.dreamsoftware.tvnexa.ui.components.CommonListItem
import com.dreamsoftware.tvnexa.ui.components.CommonLoading
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.utils.combinedLet

@Composable
fun ChannelScreenContent(
    uiState: ChannelsUiState,
    onNewCountrySelected: (CountryBO) -> Unit,
    onChannelFocused: (SimpleChannelBO) -> Unit,
    onChannelPressed: (SimpleChannelBO) -> Unit,
    onNewCategorySelected: (CategoryBO) -> Unit
) {
    with(uiState) {
        with(MaterialTheme.colorScheme) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(isLoading) {
                        CommonLoading(
                            modifier = Modifier.width(350.dp),
                            text = R.string.search_screen_search_results_loading
                        )
                    } else {
                        combinedLet(countrySelected, channelFocused) { country, channel ->
                            ChannelPreview(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.45f)
                                    .padding(start = 8.dp),
                                channel = channel,
                                country = country
                            )
                        }
                        CategoriesList(
                            categories = categories,
                            categorySelected = categorySelected,
                            onCategorySelected = onNewCategorySelected
                        )
                        ChannelsGrid(
                            channels = channels,
                            channelFocused = channelFocused,
                            onChannelFocused = onChannelFocused,
                            onChannelPressed = onChannelPressed
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoriesList(
    modifier: Modifier = Modifier,
    categories: List<CategoryBO>,
    categorySelected: CategoryBO?,
    onCategorySelected: (CategoryBO) -> Unit
) {
    TvLazyRow(
        modifier = modifier,
        state = rememberTvLazyListState(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories.size) { idx ->
            val category = categories[idx]
            CommonChip(
                isSelected = category == categorySelected,
                text = category.name,
                onSelected = {
                    onCategorySelected(category)
                }
            )
        }
    }
}

@Composable
private fun ChannelsGrid(
    modifier: Modifier = Modifier,
    channelFocused: SimpleChannelBO? = null,
    channels: List<SimpleChannelBO>,
    onChannelFocused: (SimpleChannelBO) -> Unit,
    onChannelPressed: (SimpleChannelBO) -> Unit,
) {
    CommonFocusRequester (shouldRequestFocus = {
        channels.isNotEmpty() && channelFocused != null
    }) { requester ->
        CommonLazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            state = rememberTvLazyGridState(),
            items = channels
        ) { item ->
            ChannelGridItem(
                modifier = if(item == channelFocused) Modifier.focusRequester(requester) else Modifier,
                channel = item,
                onChannelFocused = onChannelFocused,
                onChannelPressed = onChannelPressed
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
    CommonFocusRequester(shouldRequestFocus = {
        countryList.isNotEmpty() && countrySelected != null
    }) { requester ->
        Box(
            modifier = modifier,
        ) {
            TvLazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(8.dp)
            ) {
                items(countryList.size) { idx ->
                    val currentCountry = countryList[idx]
                    val isSelected = currentCountry == countrySelected
                    CommonListItem(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(8.dp)
                        .then(
                            if (isSelected)
                                Modifier.focusRequester(requester)
                            else
                                Modifier
                        ),
                        isSelected = isSelected,
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
                                    if(isFocused || isSelected) {
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
}

