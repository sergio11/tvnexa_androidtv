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
import com.dreamsoftware.tvnexa.domain.model.AvatarTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonSwitch
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
    onNsfwChanged: (Boolean) -> Unit,
    onProfileTypeChanged: (AvatarTypeEnum) -> Unit,
    onSaveProfilePressed: () -> Unit,
    onAdvanceConfigurationPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            mainTitleRes = if(isEditMode) {
                R.string.edit_profile_main_title
            } else {
                R.string.create_profile_main_title
            },
            secondaryTitleRes = if(isEditMode) {
                R.string.edit_profile_secondary_title
            } else {
                R.string.create_profile_secondary_title
            },
            primaryOptionTextRes = R.string.save_profile_confirm_button_text,
            secondaryOptionTextRes = R.string.save_profile_cancel_button_text,
            tertiaryOptionTextRes = if(isEditMode) {
                R.string.save_profile_advance_configuration_button_text
            } else {
                null
            },
            onPrimaryOptionPressed = onSaveProfilePressed,
            onSecondaryOptionPressed = onCancelPressed,
            onTertiaryOptionPressed = onAdvanceConfigurationPressed
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if(isEditMode) {
                    EditProfile(
                        uiState = uiState,
                        onAliasChanged = onAliasChanged,
                        onPinChanged = onPinChanged,
                        onNsfwChanged = onNsfwChanged,
                        onProfileTypeChanged = onProfileTypeChanged,
                    )
                } else {
                    CreateNewProfile(
                        uiState = uiState,
                        onAliasChanged = onAliasChanged,
                        onPinChanged = onPinChanged,
                        onNsfwChanged = onNsfwChanged,
                        onProfileTypeChanged = onProfileTypeChanged,
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateNewProfile(
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onNsfwChanged: (Boolean) -> Unit,
    onProfileTypeChanged: (AvatarTypeEnum) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ProfileAvatarSelected(
            profileType = uiState.profileType
        )
        ProfileSelector(
            modifier = Modifier
                .weight(0.25f),
            onProfileTypeChanged = onProfileTypeChanged
        )
    }
    Spacer(modifier = Modifier.width(30.dp))
    SaveProfileFormContent(
        uiState = uiState,
        onAliasChanged = onAliasChanged,
        onPinChanged = onPinChanged,
        onNsfwChanged = onNsfwChanged
    )
}

@Composable
private fun EditProfile(
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onNsfwChanged: (Boolean) -> Unit,
    onProfileTypeChanged: (AvatarTypeEnum) -> Unit,
) {
    ProfileAvatarSelected(
        profileType = uiState.profileType
    )
    Spacer(modifier = Modifier.width(30.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SaveProfileFormContent(
            modifier = Modifier.weight(0.7f),
            uiState = uiState,
            onAliasChanged = onAliasChanged,
            onPinChanged = onPinChanged,
            onNsfwChanged = onNsfwChanged
        )
        ProfileSelector(
            modifier = Modifier
                .weight(0.3f),
            onProfileTypeChanged = onProfileTypeChanged
        )
    }
}

@Composable
private fun ProfileAvatarSelected(
    profileType: AvatarTypeEnum?
) {
    Column(
        modifier = Modifier
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScalableAvatar(
            avatarRes = profileType?.toDrawableResource(),
            padding = Dimens.PROFILE_AVATAR_NO_PADDING,
            focusedScale = Dimens.SAVE_PROFILE_AVATAR_SELECTED_FOCUSED_SCALE
        )
        Spacer(modifier = Modifier.height(10.dp))
        CommonText(
            titleRes = R.string.save_profile_form_avatar_label_text,
            type = CommonTextTypeEnum.BODY_MEDIUM,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SaveProfileFormContent(
    modifier: Modifier = Modifier,
    uiState: SaveProfileUiState,
    onAliasChanged: (String) -> Unit,
    onPinChanged: (String) -> Unit,
    onNsfwChanged: (Boolean) -> Unit,
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
                    value = alias,
                    labelRes = R.string.save_profile_form_alias_label_text,
                    onValueChange = onAliasChanged
                )
                if(!isEditMode) {
                    Spacer(modifier = Modifier.height(20.dp))
                    CommonTextField(
                        icon = Icons.Filled.Key,
                        value = securePin,
                        type = CommonTextFieldTypeEnum.NUMBER_SECRET,
                        labelRes = R.string.save_profile_form_pin_label_text,
                        onValueChange = onPinChanged
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                CommonSwitch(
                    modifier = Modifier.width(300.dp),
                    helpTextRes = R.string.save_profile_form_is_nsfw_help_text,
                    checked = isNsfw,
                    onValueChanged = onNsfwChanged
                )
            }
        }
    }
}

@Composable
private fun ProfileSelector(
    modifier: Modifier,
    onProfileTypeChanged: (AvatarTypeEnum) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AvatarTypeEnum.entries.forEach {
            ScalableAvatar(
                avatarRes = it.toDrawableResource(),
                focusedScale = Dimens.SAVE_PROFILE_AVATAR_FOCUSED_SCALE,
                size = Dimens.SAVE_PROFILE_AVATAR_SIZE,
                padding = Dimens.PROFILE_AVATAR_NO_PADDING,
                onPressed = {
                    onProfileTypeChanged(it)
                }
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}