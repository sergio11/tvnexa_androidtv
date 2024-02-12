@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R

@Composable
fun ChannelHeaderInfo(
    modifier: Modifier = Modifier,
    name: String?,
    logo: String?,
    city: String?,
    isNsfw: Boolean?
) {
    with(MaterialTheme.colorScheme) {
        Row (modifier = modifier.height(50.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically)  {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ChannelLogo(size = 40.dp, logo = logo)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    CommonText(
                        titleText = name,
                        type = CommonTextTypeEnum.TITLE_LARGE,
                        textBold = true,
                        textColor = onPrimaryContainer
                    )
                    CommonText(
                        titleText = city,
                        type = CommonTextTypeEnum.BODY_MEDIUM,
                        textColor = onPrimaryContainer
                    )
                }
            }
            if(isNsfw == true) {
                CommonImageRes(
                    modifier = Modifier.height(40.dp),
                    imageRes = R.drawable.channel_nsfw_icon,
                    tint = onPrimaryContainer
                )
            }
        }
    }
}