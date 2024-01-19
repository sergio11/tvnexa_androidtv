@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.components.CommonButtonTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonFullScreenImage
import com.dreamsoftware.tvnexa.ui.components.CommonText
import com.dreamsoftware.tvnexa.ui.components.CommonTextField
import com.dreamsoftware.tvnexa.ui.components.CommonTextFieldTypeEnum
import com.dreamsoftware.tvnexa.ui.components.CommonTextTypeEnum
import com.dreamsoftware.tvnexa.ui.theme.TvNexaTheme

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    onSigUpButtonPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SignUpVideoBackground()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            SignUpFormContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                onCancelPressed = onCancelPressed,
                onSigUpButtonPressed = onSigUpButtonPressed
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CommonText(
                    titleText = "Build with passion by dreamsoftware. Sergio Sánchez Sánchez © 2024",
                    type = CommonTextTypeEnum.LABEL_MEDIUM,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
private fun SignUpVideoBackground() {
    CommonFullScreenImage(resourceId = R.drawable.signup_background)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    )
}

@Composable
private fun SignUpFormContent(
    modifier: Modifier,
    onSigUpButtonPressed: () -> Unit,
    onCancelPressed: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        CommonButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            type = CommonButtonTypeEnum.SMALL,
            onClick = onCancelPressed,
            inverseStyle = true,
            text = "Cancel"
        )
        Image(
            painter = painterResource(id = R.drawable.tvnexa_logo_inverse),
            contentDescription = null,
            modifier = Modifier
                .height(80.dp)
                .align(Alignment.TopEnd)
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(0.5f)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonText(
                titleText = "Explore global TV on TVNexa!",
                type = CommonTextTypeEnum.TITLE_LARGE,
            )
            Spacer(modifier = Modifier.height(10.dp))
            CommonText(
                titleText = "Sign up now for diverse channels and captivating content",
                type = CommonTextTypeEnum.TITLE_SMALL,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(0.7f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserInfoFormColumn()
            Spacer(modifier = Modifier.width(40.dp))
            UserCredentialsInfoFormColumn()
        }
        CommonButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onSigUpButtonPressed,
            text = "Create Account"
        )
    }
}

@Composable
private fun UserInfoFormColumn() {
    FormColumn {
        CommonTextField(
            icon = Icons.Filled.Person,
            value = "",
            label = "Firstname",
            onValueChange =  {}
        )
        CommonTextField(
            icon = Icons.Filled.PersonOutline,
            value = "",
            label = "Surname",
            onValueChange =  {}
        )
        CommonTextField(
            icon = Icons.Filled.Email,
            value = "",
            type = CommonTextFieldTypeEnum.EMAIL,
            label = "Email",
            onValueChange =  {}
        )
    }
}

@Composable
private fun UserCredentialsInfoFormColumn() {
    FormColumn {
        CommonTextField(
            icon = Icons.Filled.Person,
            value = "",
            label = "Username",
            onValueChange =  {}
        )
        CommonTextField(
            icon = Icons.Filled.Password,
            value = "",
            type = CommonTextFieldTypeEnum.PASSWORD,
            label = "Password",
            onValueChange =  {}
        )
        CommonTextField(
            icon = Icons.Filled.Password,
            value = "",
            type = CommonTextFieldTypeEnum.PASSWORD,
            label = "Repeat Password",
            onValueChange =  {}
        )
    }
}

@Composable
private fun FormColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Preview(device = Devices.TV_1080p)
@Composable
fun SignUpPrev() {
    TvNexaTheme {
        SignUpScreenContent(
            onSigUpButtonPressed = {},
            onCancelPressed = {}
        )
    }
}
