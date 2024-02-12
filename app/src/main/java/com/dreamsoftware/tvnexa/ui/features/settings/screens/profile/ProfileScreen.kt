@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.dreamsoftware.tvnexa.ui.features.settings.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.dreamsoftware.tvnexa.R
import com.dreamsoftware.tvnexa.ui.components.CommonButton
import com.dreamsoftware.tvnexa.ui.features.settings.components.CommonSettingsContainer

@Composable
fun ProfileScreen() {
    CommonSettingsContainer(title = "Profile") {
        ProfilesContent()
    }
}

@Composable
fun ProfilesContent() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfilePicture()
            Spacer(modifier = Modifier.size(20.dp))
            UserDetails()
        }
        Spacer(modifier = Modifier.size(5.dp))
        Row {
            Spacer(modifier = Modifier.size(120.dp))
            CommonButton(onClick = {}, modifier = Modifier.width(120.dp), text = "Save")
            Spacer(modifier = Modifier.size(16.dp))
            CommonButton(onClick = {}, modifier = Modifier.width(120.dp), text = "Cancel")
        }
    }
}

@Composable
fun ProfilePicture() {
    Image(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .shadow(elevation = 12.dp, shape = CircleShape, clip = true)
            .border(2.dp, LocalContentColor.current, CircleShape),
        painter = painterResource(id = R.drawable.profile_avatar_boy),
        contentDescription = "User profile",
    )
}

@Composable
fun UserDetails() {
    Column {
        Text(text = "Sergio Sánchez", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Android Developer",
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f),
        )
        Text(
            text = "Github: https://github.com/sergio11",
            style = MaterialTheme.typography.labelSmall,
            color = LocalContentColor.current.copy(alpha = 0.4f),
        )
    }
}