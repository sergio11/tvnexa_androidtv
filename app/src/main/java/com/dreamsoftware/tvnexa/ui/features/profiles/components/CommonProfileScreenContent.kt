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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.AppLogoInverse
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonGradientBox
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.LoadingDialog
import com.dreamsoftware.tvnexa.ui.core.UiState

@Composable
fun CommonProfileScreenContent(
    uiState: UiState<*>,
    @StringRes mainTitleRes: Int,
    @StringRes secondaryTitleRes: Int,
    @StringRes acceptRes: Int,
    @StringRes cancelRes: Int,
    onAcceptPressed: () -> Unit = {},
    onCancelPressed: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    CommonGradientBox {
        LoadingDialog(
            isShowingDialog = uiState.isLoading,
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
                .fillMaxHeight(0.9f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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
            content()
            CommonProfileActions(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f),
                acceptRes = acceptRes,
                cancelRes = cancelRes,
                onAcceptPressed = onAcceptPressed,
                onCancelPressed = onCancelPressed
            )
        }
    }
}

@Composable
private fun CommonProfileActions(
    modifier: Modifier = Modifier,
    @StringRes acceptRes: Int,
    @StringRes cancelRes: Int,
    onAcceptPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        CommonButton(
            textRes = acceptRes,
            onClick = onAcceptPressed,
        )
        Spacer(modifier = Modifier.width(30.dp))
        CommonButton(
            textRes = cancelRes,
            onClick = onCancelPressed,
            style = CommonButtonStyleTypeEnum.INVERSE
        )
    }
}