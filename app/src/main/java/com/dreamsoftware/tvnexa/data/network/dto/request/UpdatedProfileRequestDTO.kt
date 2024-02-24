package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

/**
 * Data class representing the request to update a profile.
 *
 * @property alias The new alias for the profile.
 * @property pin The new PIN for the profile.
 * @property enableNSFW Whether NSFW content is enabled for the profile.
 * @property avatarType The type of avatar for the profile.
 */
data class UpdatedProfileRequestDTO(
    @Json(name = "alias")
    val alias: String? = null,
    @Json(name = "pin")
    val pin: Int? = null,
    @Json(name = "enable_NSFW")
    val enableNSFW: Boolean? = null,
    @Json(name = "avatar_type")
    val avatarType: String? = null
)