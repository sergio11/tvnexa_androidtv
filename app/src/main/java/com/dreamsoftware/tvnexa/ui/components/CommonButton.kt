@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

private val DEFAULT_BUTTON_WIDTH = 200.dp
private val DEFAULT_BUTTON_HEIGHT = 55.dp

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    widthDp: Dp = DEFAULT_BUTTON_WIDTH,
    heightDp: Dp = DEFAULT_BUTTON_HEIGHT,
    enableBorder: Boolean = true,
    textType: CommonTextTypeEnum = CommonTextTypeEnum.TITLE_MEDIUM,
    buttonShape: Shape = RoundedCornerShape(percent = 20),
    text: String,
    inverseStyle: Boolean = false,
    onClick: () -> Unit,
) {
    with(MaterialTheme.colorScheme) {
        Button(
            onClick = onClick,
            modifier = modifier.then(
                Modifier
                    .width(widthDp)
                    .height(heightDp)
                    .clip(buttonShape)
                    .border(
                        width = if (enableBorder) {
                            2.dp
                        } else {
                            0.dp
                        },
                        color = if(inverseStyle) {
                            onSecondaryContainer
                        } else {
                            onPrimaryContainer
                        },
                        shape = buttonShape
                    )
            ),
            enabled = enabled,
            shape = ButtonDefaults.shape(shape = buttonShape),
            colors = ButtonDefaults.colors(
                containerColor = if(inverseStyle) {
                    if (enableBorder) {
                        secondaryContainer.copy(alpha = 0.8f)
                    } else {
                        secondaryContainer
                    }
                } else {
                    if (enableBorder) {
                        primaryContainer.copy(alpha = 0.8f)
                    } else {
                        primaryContainer
                    }
                },
                contentColor = if(inverseStyle) {
                    onSecondaryContainer
                } else {
                    onPrimaryContainer
                },
                focusedContainerColor = if(inverseStyle) {
                    onSecondary.copy(alpha = 0.6f)
                } else {
                    onPrimary.copy(alpha = 0.6f)
                }
            )
        ) {
            CommonText(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 6.dp),
                type = textType,
                titleText = text,
                textColor = if(inverseStyle) {
                    onSecondaryContainer
                } else {
                    onPrimaryContainer
                },
                textAlign = TextAlign.Center,
                textBold = true
            )
        }
    }
}
