package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class SimpleChannelResponseDTO(
    @Json(name = "channel_id")
    val channelId: String,
    // The name of the channel.
    @Json(name = "name")
    val name: String? = null,
    // The city or location associated with the channel (if applicable).
    @Json(name = "city")
    val city: String? = null,
    // Indicates whether the channel contains NSFW (Not Safe For Work) content.
    @Json(name = "is_nsfw")
    val isNsfw: Boolean? = null,
    // The website URL associated with the channel.
    @Json(name = "website")
    val website: String? = null,
    // The URL to the channel's logo or image.
    @Json(name = "logo")
    val logo: String? = null,
    // The stream data for the channel
    @Json(name = "streams")
    val streams: List<ChannelStreamResponseDTO>
)
