package com.dreamsoftware.tvnexa.data.network.datasource

import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException

/**
 * Interface defining the contract for data operations related to user information.
 */
interface IUserDataSource {

    /**
     * Retrieves detailed information about the user.
     *
     * @return A [UserResponseDTO] representing the detailed user information.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun getDetail(): UserResponseDTO

    /**
     * Updates the user's details.
     *
     * @param data The [UpdatedUserRequestDTO] containing the updated user information.
     * @return A [UserResponseDTO] representing the updated user information.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun updateDetail(data: UpdatedUserRequestDTO): UserResponseDTO

    /**
     * Retrieves a list of profiles associated with the user.
     *
     * @return A list of [ProfileResponseDTO] representing user profiles.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun getProfiles(): List<ProfileResponseDTO>

    /**
     * Updates a specific user profile.
     *
     * @param profileId The identifier of the profile to be updated.
     * @param data The [UpdatedProfileRequestDTO] containing the updated profile information.
     * @return A [ProfileResponseDTO] representing the updated user profile information.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestDTO): ProfileResponseDTO

    /**
     * Deletes a specific user profile.
     *
     * @param profileId The identifier of the profile to be deleted.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun deleteProfile(profileId: String): Boolean

    /**
     * Creates a new user profile.
     *
     * @param data The [CreateProfileRequestDTO] containing the information for the new profile.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun createProfile(data: CreateProfileRequestDTO): Boolean

    /**
     * Retrieves the profile information for the specified profile ID.
     *
     * @param profileId The ID of the profile to retrieve.
     * @return ProfileResponseDTO The profile information.
     * @throws NetworkException if there is a network-related issue during the operation.
     */
    @Throws(NetworkException::class)
    suspend fun getProfileById(profileId: String): ProfileResponseDTO

    /**
     * Verifies a PIN associated with a user profile.
     *
     * @param profileId The identifier of the profile for PIN verification.
     * @param data The [PinVerificationRequestDTO] containing the PIN for verification.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun verifyPin(profileId: String, data: PinVerificationRequestDTO): Boolean

    /**
     * Retrieves a list of blocked channels for a specific user profile.
     *
     * @param profileId The identifier of the profile for which blocked channels are requested.
     * @return A list of [SimpleChannelResponseDTO] representing blocked channels.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun getBlockedChannels(profileId: String): List<SimpleChannelResponseDTO>

    /**
     * Retrieves a list of favorite channels for a specific user profile.
     *
     * @param profileId The identifier of the profile for which favorite channels are requested.
     * @return A list of [SimpleChannelResponseDTO] representing favorite channels.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelResponseDTO>
}