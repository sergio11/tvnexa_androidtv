@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO

@Composable
fun ChannelPreview(
    modifier: Modifier = Modifier,
    channel: SimpleChannelBO,
    country: CountryBO
) {
    with(MaterialTheme.colorScheme) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 16.dp,
                bottomEnd = 0.dp
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            with(channel) {
                Box {
                    Log.d("ATV_CHANNEL_PREVIEW", "ChannelPreview - ${name} - ${streamUrl}")
                    CommonVideoBackground(
                        videHlsResource = streamUrl,
                        isContentBlocked = isBlocked
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(primaryContainer.copy(alpha = 0.6f))
                            .padding(vertical = 20.dp, horizontal = 15.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        CommonChannelHeaderInfo(
                            name = name,
                            logo = logo,
                            city = city,
                            isNsfw = isNsfw,
                            countryCode = country.code,
                            countryFlag = country.flag
                        )
                    }
                }
            }
        }
    }
}