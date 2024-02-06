package com.dreamsoftware.tvnexa.domain.model

data class AuthenticationBO(
    val uuid: String,
    val username: String,
    val token: String,
    val profilesCount: Int
)