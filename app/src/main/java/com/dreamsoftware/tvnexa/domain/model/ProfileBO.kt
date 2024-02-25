package com.dreamsoftware.tvnexa.domain.model

data class ProfileBO(
    val uuid: String,
    val alias: String,
    val isAdmin: Boolean,
    val isSecured: Boolean,
    val enableNSFW: Boolean,
    val avatarType: AvatarTypeEnum
)
