package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import kotlin.jvm.Throws

interface IAuthRepository {

    @Throws(
        DomainException.InvalidSessionException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun getActiveSession(): AuthSessionBO

    @Throws(
        DomainException.SigInFailedException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun signIn(email: String, password: String): AuthenticationBO

    suspend fun signUp(user: SaveUserBO): Boolean

    suspend fun signOff()
}