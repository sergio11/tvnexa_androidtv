package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import kotlin.jvm.Throws

interface IAuthRepository {

    @Throws(
        DomainException.InvalidSessionException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun getSession(): AuthSessionBO

    @Throws(
        DomainException.SigInFailedException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun signIn(email: String, password: String): AuthSessionBO

    suspend fun signUp(user: SaveUserBO): Boolean

}