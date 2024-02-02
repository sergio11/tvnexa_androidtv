package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

data class UpdatedProfileRequestDTO(
    @Json(name = "alias")
    val alias: String? = null,
    @Json(name = "pin")
    val pin: Int? = null,
    @Json(name ="is_admin")
    val isAdmin: Boolean? = null,
    @Json(name = "type")
    val type: String? = null
)
