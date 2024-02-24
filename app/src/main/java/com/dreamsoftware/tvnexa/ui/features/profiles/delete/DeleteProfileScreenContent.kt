package com.dreamsoftware.tvnexa.ui.features.profiles.delete

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonFocusRequester
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.components.ScalableAvatar
import com.dreamsoftware.tvnexa.ui.extensions.toDrawableResource
import com.dreamsoftware.tvnexa.ui.features.profiles.components.CommonProfileScreenContent
import com.dreamsoftware.tvnexa.ui.theme.Dimens

@Composable
fun DeleteProfileScreenContent(
    uiState: DeleteProfileUiState,
    onDeletePressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    with(uiState) {
        CommonProfileScreenContent(
            isLoading = isLoading,
            error = error,
            mainTitleRes = R.string.delete_profile_main_title,
            secondaryTitleRes = R.string.delete_profile_main_description,
            primaryOptionTextRes = R.string.delete_profile_form_accept_button_text,
            secondaryOptionTextRes = R.string.delete_profile_form_cancel_button_text,
            onPrimaryOptionPressed = onDeletePressed,
            onSecondaryOptionPressed = onCancelPressed
        ) {
            CommonFocusRequester {
                ScalableAvatar(
                    modifier = Modifier.focusRequester(it),
                    avatarRes = profile?.avatarType?.toDrawableResource(),
                    padding = Dimens.PROFILE_AVATAR_NO_PADDING
                )
            }
            CommonText(
                titleText = profile?.alias.orEmpty(),
                type = CommonTextTypeEnum.TITLE_LARGE,
                textBold = true
            )
            CommonText(
                modifier = Modifier.padding(horizontal = 30.dp),
                titleRes = R.string.delete_profile_explanation_text,
                type = CommonTextTypeEnum.BODY_LARGE,
                textAlign = TextAlign.Center
            )
        }
    }
}