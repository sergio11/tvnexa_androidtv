package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.SignInUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.IAuthService

/**
 * Implementation of [IAuthRemoteDataSource] responsible for handling authentication-related
 * network operations.
 *
 * @property authService The [IAuthService] instance for making authentication-related network requests.
 */
internal class AuthRemoteDataSourceImpl(
    private val authService: IAuthService
): SupportNetworkDataSource(), IAuthRemoteDataSource {

    /**
     * Companion object containing constants related to network responses.
     */
    private companion object {
        const val USER_SUCCESSFULLY_REGISTERED_CODE  = 7002
    }

    /**
     * Performs a user sign-in operation using the provided credentials.
     *
     * @param data The data containing user credentials for sign-in.
     * @return An [AuthResponseDTO] representing the authentication response.
     * @throws [NetworkException] if a network-related error occurs during the operation.
     */
    @Throws(NetworkException::class)
    override suspend fun signIn(data: SignInUserNetworkDTO): AuthResponseDTO = safeNetworkCall {
        authService.signIn(data).data
    }

    /**
     * Performs a user sign-up operation with the provided user details.
     *
     * @param data The data containing user details for sign-up.
     * @return `true` if the sign-up operation is successful, `false` otherwise.
     * @throws [NetworkException] if a network-related error occurs during the operation.
     */
    @Throws(NetworkException::class)
    override suspend fun signup(data: SignUpUserNetworkDTO): Boolean = safeNetworkCall {
        authService.signUp(data).code == USER_SUCCESSFULLY_REGISTERED_CODE
    }
}