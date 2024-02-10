package com.dreamsoftware.tvnexa.ui.features.profiles.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.AppLogoInverse
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonGradientBox
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.LoadingDialog

@Composable
fun CommonProfileScreenContent(
    isLoading: Boolean,
    @StringRes mainTitleRes: Int,
    @StringRes secondaryTitleRes: Int,
    @StringRes primaryOptionTextRes: Int,
    @StringRes secondaryOptionTextRes: Int? = null,
    @StringRes tertiaryOptionTextRes: Int? = null,
    onPrimaryOptionPressed: () -> Unit = {},
    onSecondaryOptionPressed: () -> Unit = {},
    onTertiaryOptionPressed: () -> Unit = {},
    content: @Composable (mainActionFocusRequester: FocusRequester) -> Unit
) {
    val mainActionFocusRequester = remember { FocusRequester() }
    CommonGradientBox {
        LoadingDialog(
            isShowingDialog = isLoading,
            titleRes = R.string.generic_progress_dialog_title,
            descriptionRes = R.string.generic_progress_dialog_description
        )
        AppLogoInverse(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp)
                .height(90.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(0.95f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonProfileHeader(
                mainTitleRes = mainTitleRes,
                secondaryTitleRes = secondaryTitleRes
            )
            CommonProfileContent(
                content = { content(mainActionFocusRequester) }
            )
            CommonProfileActions(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f),
                focusRequester = mainActionFocusRequester,
                primaryOptionTextRes = primaryOptionTextRes,
                secondaryOptionTextRes = secondaryOptionTextRes,
                tertiaryOptionTextRes = tertiaryOptionTextRes,
                onPrimaryOptionPressed = onPrimaryOptionPressed,
                onSecondaryOptionPressed = onSecondaryOptionPressed,
                onTertiaryOptionPressed = onTertiaryOptionPressed
            )
        }
    }
}

@Composable
private fun ColumnScope.CommonProfileHeader(
    @StringRes mainTitleRes: Int,
    @StringRes secondaryTitleRes: Int,
) {
    Spacer(modifier = Modifier.weight(0.2f))
    CommonText(
        titleRes = mainTitleRes,
        type = CommonTextTypeEnum.TITLE_LARGE
    )
    Spacer(modifier = Modifier.height(10.dp))
    CommonText(
        titleRes = secondaryTitleRes,
        type = CommonTextTypeEnum.TITLE_MEDIUM
    )
    Spacer(modifier = Modifier.weight(0.1f))
}

@Composable
private fun ColumnScope.CommonProfileContent(
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .weight(0.8f),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        content()
    }
}

@Composable
private fun CommonProfileActions(
    modifier: Modifier = Modifier,
    @StringRes primaryOptionTextRes: Int,
    @StringRes secondaryOptionTextRes: Int? = null,
    @StringRes tertiaryOptionTextRes: Int? = null,
    onPrimaryOptionPressed: () -> Unit = {},
    onSecondaryOptionPressed: () -> Unit = {},
    onTertiaryOptionPressed: () -> Unit = {},
    focusRequester: FocusRequester
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            CommonButton(
                modifier = Modifier.focusRequester(focusRequester),
                textRes = primaryOptionTextRes,
                onClick = onPrimaryOptionPressed,
            )
            secondaryOptionTextRes?.let {
                Spacer(modifier = Modifier.width(30.dp))
                CommonButton(
                    textRes = it,
                    onClick = onSecondaryOptionPressed,
                    style = CommonButtonStyleTypeEnum.INVERSE
                )
            }
        }
        tertiaryOptionTextRes?.let {
            CommonButton(
                textRes = it,
                enableBorder = false,
                onClick = onTertiaryOptionPressed,
                style = CommonButtonStyleTypeEnum.TRANSPARENT
            )
        }
    }
}