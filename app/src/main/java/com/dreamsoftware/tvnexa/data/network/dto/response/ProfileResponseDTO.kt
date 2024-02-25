package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

/**
 * Data class representing the response containing profile information.
 *
 * @property uuid The UUID of the profile.
 * @property alias The alias of the profile.
 * @property isAdmin Indicates whether the profile is an admin.
 * @property isSecured Indicates whether the profile has a secure PIN or not
 * @property enableNSFW Indicates whether NSFW content is enabled for the profile.
 * @property avatarType The type of avatar for the profile.
 */
data class ProfileResponseDTO(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "alias")
    val alias: String,
    @Json(name = "is_admin")
    val isAdmin: Boolean,
    @Json(name = "is_secured")
    val isSecured: Boolean,
    @Json(name = "enable_NSFW")
    val enableNSFW: Boolean,
    @Json(name = "avatar_type")
    val avatarType: String
)
