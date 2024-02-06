package com.dreamsoftware.tvnexa.data.preferences.dto

import com.squareup.moshi.Json

/**
 * Data class representing the selected profile preference DTO.
 * @param uuid The UUID of the selected profile.
 * @param alias The alias of the selected profile.
 * @param isAdmin Indicates whether the selected profile is an admin profile.
 * @param type The type of the selected profile.
 */
data class ProfileSelectedPreferenceDTO(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "alias")
    val alias: String,
    @Json(name = "is_admin")
    val isAdmin: Boolean,
    @Json(name = "type")
    val type: String
)
