package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO

interface IAuthRepository {

    suspend fun getSession(): AuthSessionBO

    suspend fun signIn(email: String, password: String): AuthSessionBO

    suspend fun signUp(user: SaveUserBO): Boolean

}