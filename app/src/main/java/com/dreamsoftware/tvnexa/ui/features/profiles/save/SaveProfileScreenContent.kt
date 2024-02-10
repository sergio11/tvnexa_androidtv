package com.dreamsoftware.tvnexa.ui.features.profiles.save

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextField
import com.dreamsoftware.tvnexa.ui.components.CommonTextFieldTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ScalableAvatar
import com.dreamsoftware.tvnexa.ui.extensions.toDrawableResource
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.tvnexa.ui.theme.Dimens

@Composable
fun SaveProfileScreenContent(
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onSaveProfilePressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    CommonProfileScreenContent(
        isLoading = uiState.isLoading,
        mainTitleRes = R.string.create_profile_main_title,
        secondaryTitleRes = R.string.create_profile_secondary_title,
        primaryOptionTextRes = R.string.save_profile_confirm_button_text,
        secondaryOptionTextRes = R.string.save_profile_cancel_button_text,
        onPrimaryOptionPressed = onSaveProfilePressed,
        onSecondaryOptionPressed = onCancelPressed
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp),
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                SaveProfileFormContent(
                    modifier = Modifier
                        .weight(0.7f),
                    uiState = uiState,
                    onAliasChanged = onAliasChanged,
                    onPinChanged = onPinChanged
                )
                ProfileSelector(modifier = Modifier
                    .weight(0.3f))
            }
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