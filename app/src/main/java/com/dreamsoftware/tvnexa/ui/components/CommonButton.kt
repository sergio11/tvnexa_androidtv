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
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

private val DEFAULT_BUTTON_LARGE_WIDTH = 200.dp
private val DEFAULT_BUTTON_LARGE_HEIGHT = 55.dp
private val DEFAULT_BUTTON_MEDIUM_WIDTH = 150.dp
private val DEFAULT_BUTTON_MEDIUM_HEIGHT = 40.dp
private val DEFAULT_BUTTON_SMALL_WIDTH = 100.dp
private val DEFAULT_BUTTON_SMALL_HEIGHT = 35.dp

enum class CommonButtonTypeEnum {
    SMALL,
    MEDIUM,
    LARGE
}

@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    type: CommonButtonTypeEnum = CommonButtonTypeEnum.MEDIUM,
    enableBorder: Boolean = true,
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
                    .width(when(type) {
                        CommonButtonTypeEnum.SMALL -> DEFAULT_BUTTON_SMALL_WIDTH
                        CommonButtonTypeEnum.MEDIUM -> DEFAULT_BUTTON_MEDIUM_WIDTH
                        CommonButtonTypeEnum.LARGE -> DEFAULT_BUTTON_LARGE_WIDTH
                    })
                    .height(when(type) {
                        CommonButtonTypeEnum.SMALL -> DEFAULT_BUTTON_SMALL_HEIGHT
                        CommonButtonTypeEnum.MEDIUM -> DEFAULT_BUTTON_MEDIUM_HEIGHT
                        CommonButtonTypeEnum.LARGE -> DEFAULT_BUTTON_LARGE_HEIGHT
                    })
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
                    .fillMaxSize(),
                type = when(type) {
                    CommonButtonTypeEnum.SMALL -> CommonTextTypeEnum.BODY_SMALL
                    CommonButtonTypeEnum.MEDIUM -> CommonTextTypeEnum.BODY_MEDIUM
                    CommonButtonTypeEnum.LARGE -> CommonTextTypeEnum.BODY_LARGE
                },
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
