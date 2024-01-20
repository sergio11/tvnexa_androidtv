package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

/**
 * Data Transfer Object (DTO) representing the data required to sign up a user through a network request.
 *
 * @property name The first name of the user.
 * @property lastname The last name of the user.
 * @property email The email address of the user.
 * @property phone The phone number associated with the user's account.
 * @property password The password chosen by the user for their account.
 */
data class SignUpUserNetworkDTO(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "lastname")
    val lastname: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "password")
    val password: String
)
