package com.dreamsoftware.tvnexa.data.network.service

import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ApiResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Interface defining the contract for user-related operations.
 */
interface IUserService {

    /**
     * Retrieves detailed information about the user.
     *
     * @return An [ApiResponseDTO] wrapping the [UserResponseDTO].
     */
    @GET("user/")
    suspend fun getDetail(): ApiResponseDTO<UserResponseDTO>

    /**
     * Updates the user's details.
     *
     * @param data The [UpdatedUserRequestDTO] containing the updated user information.
     * @return An [ApiResponseDTO] wrapping the updated [UserResponseDTO].
     */
    @PUT("user/")
    suspend fun updateDetail(@Body data: UpdatedUserRequestDTO): ApiResponseDTO<UserResponseDTO>

    /**
     * Retrieves a list of profiles associated with the user.
     *
     * @return An [ApiResponseDTO] wrapping a list of [ProfileResponseDTO].
     */
    @GET("user/profiles/")
    suspend fun getProfiles(): ApiResponseDTO<List<ProfileResponseDTO>>

    /**
     * Updates a specific user profile.
     *
     * @param profileId The identifier of the profile to be updated.
     * @param data The [UpdatedProfileRequestDTO] containing the updated profile information.
     * @return An [ApiResponseDTO] wrapping the updated [ProfileResponseDTO].
     */
    @PUT("user/profiles/{profileId}")
    suspend fun updateProfile(
        @Path("profileId") profileId: String,
        @Body data: UpdatedProfileRequestDTO
    ): ApiResponseDTO<ProfileResponseDTO>

    /**
     * Deletes a specific user profile.
     *
     * @param profileId The identifier of the profile to be deleted.
     * @return An [ApiResponseDTO] wrapping a message indicating the deletion status.
     */
    @DELETE("user/profiles/{profileId}")
    suspend fun deleteProfile(@Path("profileId") profileId: String): ApiResponseDTO<String>

    /**
     * Creates a new user profile.
     *
     * @param data The [CreateProfileRequestDTO] containing the information for the new profile.
     * @return An [ApiResponseDTO] wrapping a message indicating the creation status.
     */
    @POST("user/profiles/")
    suspend fun createProfile(@Body data: CreateProfileRequestDTO): ApiResponseDTO<String>

    /**
     * Verifies a PIN associated with a user profile.
     *
     * @param profileId The identifier of the profile for PIN verification.
     * @param data The [PinVerificationRequestDTO] containing the PIN for verification.
     * @return An [ApiResponseDTO] wrapping a message indicating the verification status.
     */
    @POST("user/profiles/{profileId}/verify-pin")
    suspend fun verifyPin(
        @Path("profileId") profileId: String,
        @Body data: PinVerificationRequestDTO
    ): ApiResponseDTO<Boolean>

    /**
     * Retrieves a list of blocked channels for a specific user profile.
     *
     * @param profileId The identifier of the profile for which blocked channels are requested.
     * @return An [ApiResponseDTO] wrapping a [SimpleChannelResponseDTO] representing blocked channels.
     */
    @GET("user/profiles/{profileId}/blocked-channels")
    suspend fun getBlockedChannels(@Path("profileId") profileId: String): ApiResponseDTO<List<SimpleChannelResponseDTO>>

    /**
     * Retrieves a list of favorite channels for a specific user profile.
     *
     * @param profileId The identifier of the profile for which favorite channels are requested.
     * @return An [ApiResponseDTO] wrapping a [SimpleChannelResponseDTO] representing favorite channels.
     */
    @GET("user/profiles/{profileId}/favorite-channels")
    suspend fun getFavoriteChannels(@Path("profileId") profileId: String): ApiResponseDTO<List<SimpleChannelResponseDTO>>
}
