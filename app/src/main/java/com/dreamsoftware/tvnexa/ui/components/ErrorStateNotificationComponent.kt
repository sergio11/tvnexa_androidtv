package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ErrorStateNotificationComponent(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    title: String,
    isRetryButtonVisible: Boolean = false,
    onRetryCalled: () -> Unit = {}
) {
    with(MaterialTheme.colorScheme) {
        Box(modifier = modifier
            .fillMaxSize()
            .padding(15.dp)) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(
                        Color.White.copy(alpha = 0.6f),
                        RoundedCornerShape(percent = 10)
                    )
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CommonImageRes(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp),
                    imageRes = imageRes,
                    tint = primary
                )
                CommonText(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                    titleText = title,
                    textColor = primary,
                    textAlign = TextAlign.Center
                )
                if (isRetryButtonVisible) {
                    /*CommonButton(
                        text = R.string.retry_button_text,
                        containerColor = Purple40,
                        onClick = onRetryCalled
                    )*/
                }
            }
        }
    }
}