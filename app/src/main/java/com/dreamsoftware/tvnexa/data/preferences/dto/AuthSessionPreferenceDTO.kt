package com.dreamsoftware.tvnexa.data.preferences.dto

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the structure of an authentication session stored in preferences.
 *
 * @property uuid The universally unique identifier (UUID) associated with the user.
 * @property token The authentication token associated with the user's session.
 */
data class AuthSessionPreferenceDTO(
    @Json(name = "uuid")
    val uuid: String,
    @Json(name = "token")
    val token: String
)
