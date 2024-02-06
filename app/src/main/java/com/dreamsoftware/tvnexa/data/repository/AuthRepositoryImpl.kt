package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.SignInUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.data.preferences.exception.PreferencesException
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val authRemoteDataSource: IAuthRemoteDataSource,
    private val authSessionDataSource: IAuthSessionDataSource,
    private val signupUserBOMapper: IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO>,
    private val authResponseMapper: IOneSideMapper<AuthResponseDTO, AuthenticationBO>,
    private val readAuthSessionDataBOMapper: IOneSideMapper<AuthSessionPreferenceDTO, AuthSessionBO>,
    private val saveAuthSessionDataBOMapper: IOneSideMapper<AuthenticationBO, AuthSessionPreferenceDTO>
): SupportRepositoryImpl(), IAuthRepository {

    @Throws(
        DomainException.InvalidSessionException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun getActiveSession(): AuthSessionBO = safeExecute {
        try {
            readAuthSessionDataBOMapper.mapInToOut(authSessionDataSource.get())
        } catch (ex: PreferencesException.SessionNotFoundException) {
            throw DomainException.InvalidSessionException("No session found", ex)
        }
    }

    @Throws(
        DomainException.SigInFailedException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun signIn(email: String, password: String): AuthenticationBO = safeExecute {
        authRemoteDataSource.signIn(SignInUserNetworkDTO(email, password))
            .let(authResponseMapper::mapInToOut)
            .also { saveSession(it) }
    }

    override suspend fun signUp(user: SaveUserBO): Boolean = withContext(Dispatchers.IO) {
        authRemoteDataSource.signup(signupUserBOMapper.mapInToOut(user))
    }

    private suspend fun saveSession(session: AuthenticationBO) = withContext(Dispatchers.IO) {
        authSessionDataSource.save(saveAuthSessionDataBOMapper.mapInToOut(session))
    }
}