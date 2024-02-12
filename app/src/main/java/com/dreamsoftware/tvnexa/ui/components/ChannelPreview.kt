@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO

@Composable
fun ChannelPreview(
    modifier: Modifier = Modifier,
    channel: SimpleChannelBO
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
            Box {
                Log.d("ATV_CHANNEL_PREVIEW", "ChannelPreview - ${channel.name} - ${channel.streamUrl}")
                CommonVideoBackground(
                    videHlsResource = channel.streamUrl
                )
                Row (modifier = Modifier
                    .fillMaxSize()
                    .background(primaryContainer.copy(alpha = 0.6f))
                    .padding(vertical = 20.dp, horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom)  {
                    Row(verticalAlignment = Alignment.Bottom) {
                        ChannelLogo(size = 40.dp, logo = channel.logo)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Spacer(modifier = Modifier.weight(1f))
                            CommonText(
                                titleText = channel.name,
                                type = CommonTextTypeEnum.TITLE_LARGE,
                                textBold = true,
                                textColor = onPrimaryContainer
                            )
                            CommonText(
                                titleText = channel.city,
                                type = CommonTextTypeEnum.BODY_MEDIUM,
                                textColor = onPrimaryContainer
                            )
                        }
                    }
                    if(channel.isNsfw == true) {
                        CommonImageRes(
                            modifier = Modifier.height(40.dp),
                            imageRes = R.drawable.channel_nsfw_icon,
                            tint = onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}