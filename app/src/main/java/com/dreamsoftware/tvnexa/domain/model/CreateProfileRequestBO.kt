package com.dreamsoftware.tvnexa.domain.model

data class CreateProfileRequestBO(
    val alias: String,
    val pin: Int,
    val isAdmin: Boolean,
    val type: ProfileTypeEnum
)