package com.dreamsoftware.tvnexa.domain.model

data class ChannelStreamBO(
    val code: String,
    val url: String,
    val httpReferrer: String?,
    val userAgent: String?
)
