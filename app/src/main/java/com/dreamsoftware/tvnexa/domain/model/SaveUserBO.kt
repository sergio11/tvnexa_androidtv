package com.dreamsoftware.tvnexa.domain.model

data class SaveUserBO(
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String
)
