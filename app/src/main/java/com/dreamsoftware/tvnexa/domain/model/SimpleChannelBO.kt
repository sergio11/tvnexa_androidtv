package com.dreamsoftware.tvnexa.domain.model

data class SimpleChannelBO(
    val channelId: String,
    val name: String? = null,
    val city: String? = null,
    val isNsfw: Boolean? = null,
    val website: String? = null,
    val logo: String? = null,
    val streamUrl: String? = null
)
