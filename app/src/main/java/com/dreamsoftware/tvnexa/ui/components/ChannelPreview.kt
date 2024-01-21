@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun ChannelPreview(
    modifier: Modifier = Modifier
) {
    with(MaterialTheme.colorScheme) {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            Box {
                CommonVideoBackground(
                    videoResourceId = R.raw.onboarding_screen_video_background
                )
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(primaryContainer.copy(alpha = 0.6f))
                        .padding(20.dp),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    CommonText(
                        titleText = "Channel Name",
                        type = CommonTextTypeEnum.TITLE_LARGE,
                        textBold = true,
                        textColor = onPrimaryContainer
                    )
                    CommonText(
                        titleText = "Channel Description",
                        type = CommonTextTypeEnum.BODY_MEDIUM,
                        textColor = onPrimaryContainer
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HeroItemPrev() {
    TvNexaTheme {
        ChannelPreview()
    }
}
