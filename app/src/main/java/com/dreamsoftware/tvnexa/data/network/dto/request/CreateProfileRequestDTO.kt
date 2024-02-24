package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

/**
 * Data class representing the request to create a new profile.
 *
 * @property alias The alias for the new profile.
 * @property pin The PIN for the new profile.
 * @property enableNSFW Whether NSFW content is enabled for the new profile.
 * @property avatarType The type of avatar for the new profile.
 */
data class CreateProfileRequestDTO(
    @Json(name = "alias")
    val alias: String,
    @Json(name = "pin")
    val pin: Int,
    @Json(name = "enable_NSFW")
    val enableNSFW: Boolean,
    @Json(name = "type")
    val avatarType: String
)
