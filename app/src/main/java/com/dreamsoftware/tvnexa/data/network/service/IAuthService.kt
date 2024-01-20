package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.request.SignInUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface defining authentication-related API endpoints.
 */
interface IAuthService {

    /**
     * Performs a user sign-in operation using the provided credentials.
     *
     * @param data The data containing user credentials for sign-in.
     * @return An [ApiResponseDTO] containing [AuthResponseDTO] with authentication information.
     */
    @POST("accounts/signin")
    suspend fun signIn(@Body data: SignInUserNetworkDTO): ApiResponseDTO<AuthResponseDTO>

    /**
     * Performs a user sign-up operation with the provided user details.
     *
     * @param data The data containing user details for sign-up.
     * @return An [ApiResponseDTO] containing a success message.
     */
    @POST("accounts/signup")
    suspend fun signUp(@Body data: SignUpUserNetworkDTO): ApiResponseDTO<String>
}