package com.dreamsoftware.tvnexa.ui.features.profiles.save

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.domain.model.ProfileTypeEnum
import com.dreamsoftware.tvnexa.ui.components.AppLogoInverse
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonStyleTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonGradientBox
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextField
import com.dreamsoftware.tvnexa.ui.components.CommonTextFieldTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ScalableAvatar
import com.dreamsoftware.tvnexa.ui.extensions.toDrawableResource
import com.dreamsoftware.tvnexa.ui.theme.Dimens

@Composable
fun SaveProfileScreenContent(
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onSaveProfilePressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    CommonGradientBox {
        AppLogoInverse(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(20.dp)
            .height(90.dp)
        )
        Column(modifier = Modifier
            .align(Alignment.Center)
            .fillMaxHeight(0.9f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            CommonText(
                titleRes = R.string.create_profile_main_title,
                type = CommonTextTypeEnum.TITLE_LARGE
            )
            Spacer(modifier = Modifier.height(10.dp))
            CommonText(
                titleRes = R.string.create_profile_secondary_title,
                type = CommonTextTypeEnum.TITLE_MEDIUM
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ScalableAvatar(
                        avatarRes = ProfileTypeEnum.BOY.toDrawableResource(),
                        padding = Dimens.SAVE_PROFILE_AVATAR_PADDING
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CommonText(
                        titleRes = R.string.save_profile_form_avatar_label_text,
                        type = CommonTextTypeEnum.BODY_MEDIUM,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    SaveProfileFormContent(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.7f),
                        uiState = uiState,
                        onAliasChanged = onAliasChanged,
                        onPinChanged = onPinChanged
                    )
                    ProfileSelector(modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f))
                }
            }
            SaveProfileActions(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f),
                onSaveProfilePressed = onSaveProfilePressed,
                onCancelPressed = onCancelPressed
            )
        }
    }
}

@Composable
private fun SaveProfileFormContent(
    modifier: Modifier = Modifier,
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
) {
    with(uiState) {
        CommonFocusRequester { requester ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CommonTextField(
                    modifier = Modifier.focusRequester(requester),
                    icon = Icons.Filled.Person,
                    type = CommonTextFieldTypeEnum.TEXT,
                    value = "",
                    labelRes = R.string.save_profile_form_alias_label_text,
                    onValueChange = onAliasChanged
                )
                Spacer(modifier = Modifier.height(20.dp))
                CommonTextField(
                    icon = Icons.Filled.Key,
                    value = "",
                    type = CommonTextFieldTypeEnum.NUMBER_SECRET,
                    labelRes = R.string.save_profile_form_pin_label_text,
                    onValueChange = onPinChanged
                )
            }
        }
    }
}

@Composable
private fun ProfileSelector(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ProfileTypeEnum.entries.forEach {
            ScalableAvatar(
                avatarRes = it.toDrawableResource(),
                focusedScale = Dimens.SAVE_PROFILE_AVATAR_FOCUSED_SCALE,
                size = Dimens.SAVE_PROFILE_AVATAR_SIZE,
                padding = Dimens.SAVE_PROFILE_AVATAR_PADDING
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@Composable
private fun SaveProfileActions(
    modifier: Modifier = Modifier,
    onSaveProfilePressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        CommonButton(
            textRes = R.string.save_profile_confirm_button_text,
            onClick = onSaveProfilePressed,
        )
        Spacer(modifier = Modifier.width(30.dp))
        CommonButton(
            textRes = R.string.save_profile_cancel_button_text,
            onClick = onCancelPressed,
            style = CommonButtonStyleTypeEnum.INVERSE
        )
    }
}