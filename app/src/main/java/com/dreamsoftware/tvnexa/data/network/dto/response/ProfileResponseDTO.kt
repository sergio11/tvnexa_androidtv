package com.dreamsoftware.tvnexa.data.network.dto.response

import com.squareup.moshi.Json

data class ProfileResponseDTO(
    //The unique identifier of the profile.
    @Json(name = "uuid")
    val uuid: String,
    // The alias associated with the profile.
    @Json(name = "alias")
    val alias: String,
    // Indicates whether the profile is an admin.
    @Json(name = "is_admin")
    val isAdmin: Boolean,
    // The type of the profile.
    @Json(name = "type")
    val type: String
)
