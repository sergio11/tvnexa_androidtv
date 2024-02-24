package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
fun CommonLoading(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
) {
    with(MaterialTheme.colorScheme) {
        Box(modifier = modifier
            .fillMaxSize()
            .padding(15.dp)) {
            Column(
                modifier = modifier
                    .align(Alignment.Center)
                    .background(
                        Color.White.copy(alpha = 0.6f),
                        RoundedCornerShape(percent = 10)
                    )
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonText(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    type = CommonTextTypeEnum.TITLE_MEDIUM,
                    titleRes = text,
                    textColor = primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp),
                    color = primary
                )
            }
        }
    }
}