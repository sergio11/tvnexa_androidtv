@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonButtonTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonFullScreenImage
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextField
import com.dreamsoftware.tvnexa.ui.components.CommonTextFieldTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ErrorDialogDialog
import com.dreamsoftware.tvnexa.ui.components.LoadingDialog
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    uiState: SignInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSigInPressed: () -> Unit,
    onErrorAcceptPressed: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    SignInDialog(
        uiState = uiState,
        onErrorAcceptPressed = onErrorAcceptPressed
    )
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SignInVideoBackground()
        SignInLogo()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.Center,
        ) {
            SignInMainContent(
                uiState = uiState,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged,
                onSigInPressed = onSigInPressed
            )
            SignInSecondaryContent(onGoToSignUp = onGoToSignUp)
        }
    }
}

@Composable
private fun SignInDialog(
    uiState: SignInUiState,
    onErrorAcceptPressed: () -> Unit
) {
    with(uiState) {
        LoadingDialog(
            isShowingDialog = isLoading,
            titleRes = R.string.sign_in_progress_dialog_title,
            descriptionRes = R.string.sign_in_progress_dialog_description
        )
        ErrorDialogDialog(
            isVisible = !error.isNullOrBlank(),
            description = error,
            onAcceptClicked = onErrorAcceptPressed
        )
    }
}

@Composable
private fun BoxScope.SignInLogo() {
    Image(
        painter = painterResource(id = R.drawable.tvnexa_logo_inverse),
        contentDescription = null,
        modifier = Modifier
            .height(120.dp)
            .padding(horizontal = 20.dp)
            .align(Alignment.TopStart)
    )
}

@Composable
private fun SignInMainContent(
    uiState: SignInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSigInPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val commonModifier = Modifier
            .fillMaxHeight()
            .weight(1f)
        SignInFormInfo(modifier = commonModifier)
        SignInFormContent(
            modifier = commonModifier,
            uiState = uiState,
            onEmailChanged = onEmailChanged,
            onPasswordChanged = onPasswordChanged,
            onSigInPressed = onSigInPressed
        )
    }
}

@Composable
private fun ColumnScope.SignInSecondaryContent(onGoToSignUp: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.3f)
            .align(Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CommonText(
                titleRes = R.string.developer_credits_text,
                type = CommonTextTypeEnum.LABEL_MEDIUM,
                textAlign = TextAlign.Center
            )
        }
        CommonText(
            titleRes = R.string.sign_in_do_not_have_account_yet_text,
            type = CommonTextTypeEnum.LABEL_LARGE
        )
        Spacer(modifier = Modifier.width(20.dp))
        CommonButton(
            textRes = R.string.sign_in_go_sign_up_button_text,
            type = CommonButtonTypeEnum.SMALL,
            style = CommonButtonStyleTypeEnum.TRANSPARENT,
            onClick = onGoToSignUp
        )
    }
}

@Composable
private fun SignInVideoBackground() {
    CommonFullScreenImage(resourceId = R.drawable.login_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    )
}


@Composable
private fun SignInFormInfo(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CommonText(
            titleRes = R.string.sign_in_main_title_text,
            type = CommonTextTypeEnum.HEADLINE_MEDIUM,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        CommonText(
            titleRes = R.string.sign_in_secondary_title_text,
            type = CommonTextTypeEnum.BODY_LARGE,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun SignInFormContent(
    modifier: Modifier,
    uiState: SignInUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSigInPressed: () -> Unit
) {
    with(uiState) {
        CommonFocusRequester { requester ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CommonText(
                    titleRes = R.string.sign_in_form_heading_text,
                    type = CommonTextTypeEnum.HEADLINE_MEDIUM,
                )
                Spacer(modifier = Modifier.height(20.dp))
                CommonTextField(
                    icon = Icons.Filled.Person,
                    value = email,
                    type = CommonTextFieldTypeEnum.EMAIL,
                    labelRes = R.string.sign_in_form_email_label_text,
                    errorMessage = emailError,
                    onValueChange = onEmailChanged
                )
                Spacer(modifier = Modifier.height(20.dp))
                CommonTextField(
                    icon = Icons.Filled.Password,
                    value = password,
                    type = CommonTextFieldTypeEnum.PASSWORD,
                    labelRes = R.string.sign_in_form_password_label_text,
                    errorMessage = passwordError,
                    onValueChange = onPasswordChanged
                )
                Spacer(modifier = Modifier.height(40.dp))
                CommonButton(
                    modifier = Modifier.focusRequester(requester),
                    onClick = onSigInPressed,
                    textRes = R.string.sign_in_main_button_text
                )
            }
        }
    }
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignInScreenContentPrev() {
    TvNexaTheme {
        SignInScreenContent(
            uiState = SignInUiState(),
            onGoToSignUp = {},
            onEmailChanged = {},
            onPasswordChanged = {},
            onSigInPressed = {},
            onErrorAcceptPressed = {}
        )
    }
}
