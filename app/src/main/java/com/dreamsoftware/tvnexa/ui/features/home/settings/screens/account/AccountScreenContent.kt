@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.home.settings.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonDialog
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.features.home.settings.components.CommonSettingsContainer

@Composable
fun AccountScreenContent(
    uiState: AccountUiState,
    onSignOffPressed: () -> Unit,
    onConfirmSignOff: () -> Unit,
    onCancelSignOff: () -> Unit
) {
    ConfirmSignOffDialog(
        isVisible = uiState.confirmSignOffDialogVisible,
        onConfirmSignOff = onConfirmSignOff,
        onCancelSignOff = onCancelSignOff
    )
    CommonSettingsContainer(titleRes = R.string.account_title) {
        Column {
            AccountDetails(uiState = uiState)
            Spacer(modifier = Modifier.height(50.dp))
            CommonButton(
                onClick = onSignOffPressed,
                textRes = R.string.account_sign_off_button_text
            )
        }
    }
}

@Composable
private fun AccountDetails(uiState: AccountUiState) {
    val textColor = MaterialTheme.colorScheme.onSurface
    with(uiState) {
        Column {
            CommonText(
                titleText = "$firstName $lastName",
                type = CommonTextTypeEnum.HEADLINE_SMALL,
                textBold = true,
                textColor = textColor
            )
            Spacer(modifier = Modifier.size(10.dp))
            CommonText(
                titleText = username,
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                textColor = textColor
            )
            Spacer(modifier = Modifier.size(8.dp))
            CommonText(
                titleText = email,
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                textColor = textColor
            )
            Spacer(modifier = Modifier.size(8.dp))
            CommonText(
                titleText = uuid,
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                textColor = textColor
            )
        }
    }
}

@Composable
private fun ConfirmSignOffDialog(
    isVisible: Boolean,
    onConfirmSignOff: () -> Unit,
    onCancelSignOff: () -> Unit
) {
    CommonDialog(
        isVisible = isVisible,
        titleRes = R.string.account_sign_off_dialog_title_text,
        descriptionRes = R.string.account_sign_off_dialog_description_text,
        onCancelClicked = onCancelSignOff,
        onAcceptClicked = onConfirmSignOff
    )
}