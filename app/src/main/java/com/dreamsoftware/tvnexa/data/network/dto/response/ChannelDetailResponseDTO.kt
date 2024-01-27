package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class ChannelDetailResponseDTO(
    @Json(name = "channel_id")
    val channelId: String,
    // The name of the channel.
    @Json(name = "name")
    val name: String? = null,
    // The network to which the channel belongs.
    @Json(name = "network")
    val network: String? = null,
    // The country where the channel is based.
    @Json(name = "country")
    val country: CountryResponseDTO,
    // The subdivision or region within the country (if applicable).
    @Json(name = "subdivision")
    val subdivision: SubdivisionResponseDTO? = null,
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
    // The date when the channel was launched or started.
    @Json(name = "launched")
    val launched: String? = null,
    // The date when the channel was closed (if applicable).
    @Json(name = "closed")
    val closed: String? = null,
    // The channel that replaced this channel (if applicable).
    @Json(name = "replaced_by")
    val replacedBy: ChannelDetailResponseDTO? = null,
    // The stream data for the channel
    @Json(name = "streams")
    val streams: List<ChannelStreamResponseDTO>,
    // Guides of the channel
    @Json(name = "guides")
    val guides: List<ChannelGuideResponseDTO>,
    // The languages spoken or used by the channel.
    @Json(name = "languages")
    val languages: List<LanguageResponseDTO>,
    // The categories or genres to which the channel belongs.
    @Json(name = "categories")
    val categories: List<CategoryResponseDTO>,
    // List of alternative channel names.
    @Json(name = "alt_names")
    val altNames: List<String>,
    // List of channel owners.
    @Json(name = "owners")
    val owners: List<String>,
    // List of codes describing the broadcasting area (r/<region_code>, c/<country_code>, s/<subdivision_code>)
    @Json(name = "broadcast_areas")
    val broadcastAreas: List<String>,
)
