package com.dreamsoftware.tvnexa.ui.features.profiles.secure

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextField
import com.dreamsoftware.tvnexa.ui.components.CommonTextFieldTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent

@Composable
fun SecurePinScreenContent(
    uiState: SecurePinUiState,
    onUnlockPinChanged: (unlockPin: String) -> Unit,
    onVerifyPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            mainTitleRes = R.string.secure_pin_main_title,
            secondaryTitleRes = R.string.secure_pin_main_description,
            primaryOptionTextRes = R.string.secure_pin_form_accept_button_text,
            secondaryOptionTextRes = R.string.secure_pin_form_cancel_button_text,
            onPrimaryOptionPressed = onVerifyPressed,
            onSecondaryOptionPressed = onCancelPressed
        ) { mainFocusRequester ->
            CommonFocusRequester { focusRequester ->
                CommonTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    icon = Icons.Filled.Security,
                    value = unlockPin,
                    type = CommonTextFieldTypeEnum.NUMBER,
                    imeAction = ImeAction.Done,
                    onImeActionCompleted = {
                        mainFocusRequester.requestFocus()
                    },
                    labelRes = R.string.secure_pin_form_label_text,
                    onValueChange = onUnlockPinChanged
                )
            }
            profileLocked?.let {
                CommonText(
                    type = CommonTextTypeEnum.BODY_MEDIUM,
                    titleText = stringResource(id = R.string.secure_pin_info_profile_locked, it.alias),
                    textBold = true
                )
            }
        }
    }
}