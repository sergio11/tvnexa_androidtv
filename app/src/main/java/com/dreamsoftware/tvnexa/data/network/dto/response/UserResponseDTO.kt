package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the structure of a user API response.
 *
 * @property uuid The universally unique identifier (UUID) associated with the user.
 * @property username The username of the user.
 * @property email The email address of the user.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 */
data class UserResponseDTO(
    @field:Json(name = "uuid")
    val uuid: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "firstName")
    val firstName: String,
    @field:Json(name = "lastName")
    val lastName: String
)
