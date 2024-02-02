package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

/**
 * Data class representing the request payload for creating a new user profile.
 *
 * @property alias The alias or username for the new user profile.
 * @property pin The personal identification number (PIN) associated with the profile.
 * @property isAdmin A boolean indicating whether the user profile has administrative privileges.
 * @property type The type of the user profile, specifying its characteristics.
 */
data class CreateProfileRequestDTO(
    @Json(name = "alias")
    val alias: String,

    @Json(name = "pin")
    val pin: Int,

    @Json(name = "is_admin")
    val isAdmin: Boolean,

    @Json(name = "type")
    val type: String
)
