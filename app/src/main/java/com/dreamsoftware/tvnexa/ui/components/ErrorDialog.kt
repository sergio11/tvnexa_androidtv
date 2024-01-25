package com.dreamsoftware.tvnexa.ui.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.dreamsoftware.tvnexa.R

@Composable
fun ErrorDialogDialog(
    isVisible: Boolean,
    @StringRes titleRes: Int = R.string.dialog_error_title,
    description: String?,
    onAcceptClicked: () -> Unit,
) {
    CommonDialog(
        isVisible = isVisible,
        titleRes = titleRes,
        description = description,
        onAcceptClicked = onAcceptClicked
    )
}
