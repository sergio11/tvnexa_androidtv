package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class ChannelStreamResponseDTO(
    // The code of the channel stream.
    @Json(name = "code")
    val code: String,
    // The URL of the channel stream.
    @Json(name = "url")
    val url: String,
    // The HTTP referrer header (if provided).
    @Json(name = "http_referrer")
    val httpReferrer: String? = null,
    // The User-Agent header (if provided).
    @Json(name = "user_agent")
    val userAgent: String? = null
)
