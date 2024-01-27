package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the structure of an authentication API response.
 *
 * @property token The authentication token associated with the user.
 * @property user The user information as part of the authentication response.
 *
 * @param UserResponseDTO The type representing user information in the API response.
 */
data class AuthResponseDTO(
    // Authentication token generated upon successful authentication.
    @Json(name = "token")
    val token: String,
    // User information associated with the authenticated user.
    @Json(name = "user")
    val user: UserResponseDTO,
)
