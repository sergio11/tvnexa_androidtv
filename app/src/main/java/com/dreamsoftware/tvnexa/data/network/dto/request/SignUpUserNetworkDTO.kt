package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the structure of user data for sign-up in the network layer.
 *
 * @property username The username of the user.
 * @property password The password associated with the user.
 * @property email The email address of the user.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 */
data class SignUpUserNetworkDTO(
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "password")
    val password: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "firstName")
    val firstName: String,
    @field:Json(name = "lastName")
    val lastName: String
)
