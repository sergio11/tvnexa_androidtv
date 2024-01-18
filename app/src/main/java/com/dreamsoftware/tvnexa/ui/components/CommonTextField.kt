@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme

enum class CommonTextFieldTypeEnum {
    NUMBER,
    TEXT,
    PHONE,
    EMAIL,
    PASSWORD
}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    type: CommonTextFieldTypeEnum = CommonTextFieldTypeEnum.TEXT,
    value: String,
    enabled: Boolean = true,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    errorMessage: String? = null
) {
    with(MaterialTheme.colorScheme) {
        val isError = !errorMessage.isNullOrBlank()
        OutlinedTextField(
            modifier = modifier,
            value = value,
            enabled = enabled,
            colors = TextFieldDefaults.colors(
                focusedLabelColor = onPrimary,
                unfocusedLabelColor = primary
            ),
            visualTransformation = if (type != CommonTextFieldTypeEnum.PASSWORD)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            onValueChange = { text ->
                onValueChange(text)
            },
            label = {
                Text(text = label)
            },
            trailingIcon = {
                if (isError) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = "error",
                        tint = errorContainer
                    )
                }
            },
            isError = isError,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    tint = primary,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = when (type) {
                    CommonTextFieldTypeEnum.TEXT -> KeyboardType.Text
                    CommonTextFieldTypeEnum.EMAIL -> KeyboardType.Email
                    CommonTextFieldTypeEnum.NUMBER -> KeyboardType.Number
                    CommonTextFieldTypeEnum.PHONE -> KeyboardType.Phone
                    CommonTextFieldTypeEnum.PASSWORD -> KeyboardType.Password
                }
            )
        )
        if (isError) {
            CommonText(
                modifier = Modifier.padding(top = 5.dp),
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                titleText = errorMessage.orEmpty(),
                textColor = errorContainer
            )
        }
    }
}
