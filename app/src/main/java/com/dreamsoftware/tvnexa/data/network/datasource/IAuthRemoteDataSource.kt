package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.request.SignInUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * Interface defining methods for handling authentication-related network operations.
 */
interface IAuthRemoteDataSource {

    /**
     * Performs a user sign-in operation using the provided credentials.
     *
     * @param data The data containing user credentials for sign-in.
     * @return An [AuthResponseDTO] representing the authentication response.
     * @throws [NetworkException] if a network-related error occurs during the operation.
     */
    @Throws(NetworkException::class)
    suspend fun signIn(data: SignInUserNetworkDTO): AuthResponseDTO

    /**
     * Performs a user sign-up operation with the provided user details.
     *
     * @param data The data containing user details for sign-up.
     * @return `true` if the sign-up operation is successful, `false` otherwise.
     * @throws [NetworkException] if a network-related error occurs during the operation.
     */
    @Throws(NetworkException::class)
    suspend fun signup(data: SignUpUserNetworkDTO): Boolean
}