package com.dreamsoftware.tvnexa.ui.features.profiles.components

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonDialog
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonTextField
import com.dreamsoftware.tvnexa.ui.components.CommonTextFieldTypeEnum
import com.dreamsoftware.tvnexa.ui.extensions.EMPTY

@Composable
fun ProfileLockedDialog(
    isVisible: Boolean,
    onDismissPressed: () -> Unit,
    onUnlockPressed: (String) -> Unit
) {
    BackHandler { onDismissPressed() }
    var unlockPin by remember { mutableStateOf(String.EMPTY) }
    CommonDialog(
        isVisible = isVisible,
        titleRes = R.string.profile_selector_unlock_dialog_title,
        descriptionRes = R.string.profile_selector_unlock_dialog_description,
    ) {
        CommonFocusRequester { focusRequester ->
            CommonTextField(
                modifier = Modifier.focusRequester(focusRequester),
                icon = Icons.Filled.Security,
                value = unlockPin,
                type = CommonTextFieldTypeEnum.NUMBER,
                imeAction = ImeAction.Done,
                labelRes = R.string.profile_selector_form_unlock_pin_label_text,
                onValueChange = {
                    unlockPin = it
                    if(unlockPin.length >= 6) {
                        onUnlockPressed(unlockPin)
                        unlockPin = String.EMPTY
                    }
                }
            )
        }
    }
}
