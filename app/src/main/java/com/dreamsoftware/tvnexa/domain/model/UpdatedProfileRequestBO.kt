package com.dreamsoftware.tvnexa.domain.model

data class UpdatedProfileRequestBO(
    val alias: String? = null,
    val pin: Int? = null,
    val enableNSFW: Boolean? = null,
    val avatarType: String? = null
)
