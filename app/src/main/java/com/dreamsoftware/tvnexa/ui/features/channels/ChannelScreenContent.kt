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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.dreamsoftware.tvnexa.ui.components.CommonListItem
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.features.channels.carousel.HomeCarousel
import com.dreamsoftware.tvnexa.ui.features.channels.hero.HeroItem

typealias FocusPosition = Pair<Int, Int>

@Composable
fun ChannelScreenContent(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit
) {
    val focusState = remember { mutableStateOf(FocusPosition(0, 0)) }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountryListColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.2f)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            HeroItem()
            HomeCarousel(Modifier.weight(1f), onItemFocus = { parent, child ->
                focusState.value = FocusPosition(parent, child)
                onItemFocus(parent, child)
            }, onItemClick = onItemClick)
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CountryListColumn(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
    ) {
        TvLazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(15) { idx ->
                CommonListItem(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp),
                    onClicked = {  }
                ) { isFocused ->
                    CommonText(
                        type = CommonTextTypeEnum.TITLE_MEDIUM,
                        titleText = "Country - $idx",
                        textColor = with(MaterialTheme.colorScheme) {
                            onPrimaryContainer
                        }
                    )
                }
            }
        }
    }
}

