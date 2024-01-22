package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.SignInUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.utils.IMapper
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val authRemoteDataSource: IAuthRemoteDataSource,
    private val authSessionDataSource: IAuthSessionDataSource,
    private val signupUserBOMapper: IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO>,
    private val authResponseMapper: IOneSideMapper<AuthResponseDTO, AuthSessionBO>,
    private val authSessionBOMapper: IMapper<AuthSessionPreferenceDTO, AuthSessionBO>
): IAuthRepository {
    override suspend fun getSession(): AuthSessionBO = withContext(Dispatchers.IO) {
        authSessionBOMapper.mapInToOut(authSessionDataSource.get())
    }

    override suspend fun hasActiveSession(): Boolean = withContext(Dispatchers.IO) {
        runCatching { authSessionDataSource.get() }.getOrNull() != null
    }

    override suspend fun signIn(email: String, password: String): AuthSessionBO = withContext(Dispatchers.IO) {
        authRemoteDataSource.signIn(SignInUserNetworkDTO(email, password)).let {
            authResponseMapper.mapInToOut(it)
        }.also { saveSession(it) }
    }

    override suspend fun signUp(user: SaveUserBO): Boolean = withContext(Dispatchers.IO) {
        authRemoteDataSource.signup(signupUserBOMapper.mapInToOut(user))
    }

    private suspend fun saveSession(session: AuthSessionBO) = withContext(Dispatchers.IO) {
        authSessionDataSource.save(authSessionBOMapper.mapOutToIn(session))
    }
}