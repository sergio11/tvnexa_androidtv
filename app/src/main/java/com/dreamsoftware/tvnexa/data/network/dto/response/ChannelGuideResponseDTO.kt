package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class ChannelGuideResponseDTO(
    // The code of the channel stream.
    @field:Json(name = "code")
    val code: String,
    // The site or source of the channel guide information.
    @field:Json(name = "site")
    val site: String,
    // Unique channel ID used on the site.
    @field:Json(name = "site_id")
    val siteId: String,
    // Channel name used on the site
    @field:Json(name = "site_name")
    val siteName: String,
    // The language of the channel guide.
    @field:Json(name = "lang")
    val lang: String
)
