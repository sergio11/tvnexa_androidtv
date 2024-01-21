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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.dreamsoftware.tvnexa.ui.components.ChannelPreview
import com.dreamsoftware.tvnexa.ui.components.CommonLazyVerticalGrid
import com.dreamsoftware.tvnexa.ui.components.CommonListItem
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum

@Composable
fun ChannelScreenContent(
    onItemFocus: (parent: Int, child: Int) -> Unit,
    onItemClick: (parent: Int, child: Int) -> Unit
) {
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
        ChannelsGrid(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )

    }
}

@Composable
private fun ChannelsGrid(
    modifier: Modifier
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
            items = mutableListOf<String>().apply {
                repeat(times = 100) { idx ->
                    add("Channel $idx")
                }
            }
        ) { item ->
            CommonListItem(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
                onClicked = {  }
            ) { isFocused ->
                CommonText(
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                    titleText = item,
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

