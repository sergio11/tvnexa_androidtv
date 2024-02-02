package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

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
