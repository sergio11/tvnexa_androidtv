package com.dreamsoftware.tvnexa.domain.model

data class AuthSessionBO(
    val uuid: String,
    val username: String,
    val token: String
)