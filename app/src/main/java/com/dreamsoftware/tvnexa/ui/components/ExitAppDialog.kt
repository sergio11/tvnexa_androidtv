package com.dreamsoftware.tvnexa.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun ExitAppDialog(
    onDismissPressed: () -> Unit,
    onExitPressed: () -> Unit
) {
    Dialog(onDismissRequest = onDismissPressed) {
        CommonDialogUI(
            onDismissPressed = onDismissPressed,
            onExitPressed = onExitPressed
        )
    }
}
