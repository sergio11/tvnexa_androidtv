@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.login

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text

@Composable
fun ScreenHeading(heading: String) {
    Text(
        text = heading,
        style = MaterialTheme.typography.headlineLarge,
    )
}

@Preview
@Composable
fun ScreenHeadingPrev() {
    ScreenHeading("LOGIN")
}
