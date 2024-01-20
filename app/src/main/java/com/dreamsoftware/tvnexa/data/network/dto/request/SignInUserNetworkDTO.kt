package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the data required to sign in a user through a network request.
 *
 * @property email The email address of the user.
 * @property password The password associated with the user's account.
 */
data class SignInUserNetworkDTO(
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "password")
    val password: String
)
