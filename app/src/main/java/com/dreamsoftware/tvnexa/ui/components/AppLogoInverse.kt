package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dreamsoftware.tvnexa.R

@Composable
fun AppLogoInverse(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.tvnexa_logo_inverse),
        contentDescription = null,
        modifier = modifier
    )
}