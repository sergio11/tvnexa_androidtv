package com.dreamsoftware.tvnexa.data.network.dto.request

import com.squareup.moshi.Json

data class UpdatedUserRequestDTO(
    //Updated first name of the user.
    @Json(name = "firstName")
    val firstName: String? = null,
    // Updated last name of the user.
    @Json(name = "lastName")
    val lastName: String? = null,
    // Updated username of the user.
    @Json(name =  "username")
    val username: String? = null
)
