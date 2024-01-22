package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class ChannelStreamResponseDTO(
    // The code of the channel stream.
    @field:Json(name = "code")
    val code: String,
    // The URL of the channel stream.
    @field:Json(name = "url")
    val url: String,
    // The HTTP referrer header (if provided).
    @field:Json(name = "http_referrer")
    val httpReferrer: String? = null,
    // The User-Agent header (if provided).
    @field:Json(name = "user_agent")
    val userAgent: String? = null
)
