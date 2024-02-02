package com.dreamsoftware.tvnexa.domain.model

data class UpdatedProfileRequestBO(
    val alias: String? = null,
    val pin: Int? = null,
    val isAdmin: Boolean? = null,
    val type: String? = null
)
