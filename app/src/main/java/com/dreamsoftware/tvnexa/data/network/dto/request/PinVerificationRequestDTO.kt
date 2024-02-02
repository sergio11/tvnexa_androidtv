package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

data class PinVerificationRequestDTO(
    @Json(name = "pin")
    val pin: Int
)
