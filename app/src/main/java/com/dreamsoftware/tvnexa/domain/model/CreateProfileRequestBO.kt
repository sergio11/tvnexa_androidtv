package com.dreamsoftware.tvnexa.domain.model

data class CreateProfileRequestBO(
    val alias: String,
    val pin: Int?,
    val enableNSFW: Boolean,
    val avatarType: AvatarTypeEnum
)