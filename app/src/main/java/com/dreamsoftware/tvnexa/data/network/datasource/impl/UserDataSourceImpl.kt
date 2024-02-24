package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IUserDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.IUserService

/**
 * Implementation of the [IUserDataSource] interface responsible for handling user-related data operations.
 *
 * This class utilizes the [IUserService] to interact with the backend API and extends [SupportNetworkDataSource]
 * to benefit from network-related utility functions.
 *
 * @property userService An instance of [IUserService] used for making network requests.
 */
internal class UserDataSourceImpl(
    private val userService: IUserService
): SupportNetworkDataSource(), IUserDataSource {

    /**
     * Companion object containing constants for success codes related to user profiles.
     */
    private companion object {
        const val USER_PROFILE_CREATED_SUCCESSFULLY_CODE = 8006
        const val USER_PROFILE_DELETED_SUCCESSFULLY = 8005
        const val USER_PROFILE_VERIFICATION_SUCCESSFULLY = 8008
        const val SAVE_FAVORITE_CHANNEL_SUCCESSFULLY = 8014
        const val DELETE_FAVORITE_CHANNEL_SUCCESSFULLY = 8015
        const val CHANNEL_BLOCKED_SUCCESSFULLY = 8011
        const val CHANNEL_UNBLOCKED_SUCCESSFULLY = 8012
    }

    /**
     * Retrieves detailed information about the user.
     *
     * @return A [UserResponseDTO] representing the detailed user information.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun getDetail(): UserResponseDTO = safeNetworkCall {
        userService.getDetail().data
    }

    /**
     * Updates the user's details.
     *
     * @param data The [UpdatedUserRequestDTO] containing the updated user information.
     * @return A [UserResponseDTO] representing the updated user information.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun updateDetail(data: UpdatedUserRequestDTO): UserResponseDTO = safeNetworkCall {
        userService.updateDetail(data).data
    }

    /**
     * Retrieves a list of profiles associated with the user.
     *
     * @return A list of [ProfileResponseDTO] representing user profiles.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun getProfiles(): List<ProfileResponseDTO> = safeNetworkCall {
        userService.getProfiles().data
    }

    /**
     * Updates a specific user profile.
     *
     * @param profileId The identifier of the profile to be updated.
     * @param data The [UpdatedProfileRequestDTO] containing the updated profile information.
     * @return A [ProfileResponseDTO] representing the updated user profile information.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun updateProfile(
        profileId: String,
        data: UpdatedProfileRequestDTO
    ): ProfileResponseDTO = safeNetworkCall {
        userService.updateProfile(profileId, data).data
    }

    /**
     * Deletes a specific user profile.
     *
     * @param profileId The identifier of the profile to be deleted.
     * @return `true` if the profile is successfully deleted, `false` otherwise.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun deleteProfile(profileId: String): Boolean = safeNetworkCall {
        userService.deleteProfile(profileId).code == USER_PROFILE_DELETED_SUCCESSFULLY
    }

    /**
     * Creates a new user profile.
     *
     * @param data The [CreateProfileRequestDTO] containing the information for the new profile.
     * @return `true` if the profile is successfully created, `false` otherwise.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun createProfile(data: CreateProfileRequestDTO): Boolean = safeNetworkCall {
        userService.createProfile(data).code == USER_PROFILE_CREATED_SUCCESSFULLY_CODE
    }

    /**
     * Retrieves the profile information for the specified profile ID.
     *
     * @param profileId The ID of the profile to retrieve.
     * @return ProfileResponseDTO The profile information.
     * @throws NetworkException if there is a network-related issue during the operation.
     */
    @Throws(NetworkException::class)
    override suspend fun getProfileById(profileId: String): ProfileResponseDTO = safeNetworkCall {
        userService.getProfile(profileId).data
    }

    /**
     * Verifies a PIN associated with a user profile.
     *
     * @param profileId The identifier of the profile for PIN verification.
     * @param data The [PinVerificationRequestDTO] containing the PIN for verification.
     * @return `true` if the PIN is successfully verified, `false` otherwise.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun verifyPin(profileId: String, data: PinVerificationRequestDTO): Boolean = safeNetworkCall {
        userService.verifyPin(profileId, data).code == USER_PROFILE_VERIFICATION_SUCCESSFULLY
    }

    /**
     * Retrieves a list of blocked channels for a specific user profile.
     *
     * @param profileId The identifier of the profile for which blocked channels are requested.
     * @return A list of [SimpleChannelResponseDTO] representing blocked channels.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun getBlockedChannels(profileId: String): List<SimpleChannelResponseDTO> = safeNetworkCall {
        userService.getBlockedChannels(profileId).data
    }

    /**
     * Saves the blocking status of a channel for a specific user profile.
     *
     * @param profileId The ID of the user profile.
     * @param channelId The ID of the channel to be blocked.
     * @return `true` if the operation is successful, `false` otherwise.
     * @throws NetworkException if there's an issue with the network connection.
     */
    @Throws(NetworkException::class)
    override suspend fun blockChannel(profileId: String, channelId: String): Boolean = safeNetworkCall {
        userService.blockChannel(profileId, channelId).code == CHANNEL_BLOCKED_SUCCESSFULLY
    }

    /**
     * Deletes the blocking status of a channel for a specific user profile.
     *
     * @param profileId The ID of the user profile.
     * @param channelId The ID of the channel to be unblocked.
     * @return `true` if the operation is successful, `false` otherwise.
     * @throws NetworkException if there's an issue with the network connection.
     */
    @Throws(NetworkException::class)
    override suspend fun unblockChannel(profileId: String, channelId: String): Boolean = safeNetworkCall {
        userService.unblockChannel(profileId, channelId).code == CHANNEL_UNBLOCKED_SUCCESSFULLY
    }

    /**
     * Retrieves a list of favorite channels for a specific user profile.
     *
     * @param profileId The identifier of the profile for which favorite channels are requested.
     * @return A list of [SimpleChannelResponseDTO] representing favorite channels.
     * @throws NetworkException if there is a network-related error.
     */
    @Throws(NetworkException::class)
    override suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelResponseDTO> = safeNetworkCall {
        userService.getFavoriteChannels(profileId).data
    }

    /**
     * Saves a channel as a favorite for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be saved as a favorite.
     * @return Boolean true if the operation is successful, false otherwise.
     * @throws NetworkException if there is a network-related issue during the operation.
     */
    @Throws(NetworkException::class)
    override suspend fun saveFavoriteChannels(profileId: String, channelId: String): Boolean = safeNetworkCall {
        userService.saveFavoriteChannels(profileId, channelId).code == SAVE_FAVORITE_CHANNEL_SUCCESSFULLY
    }

    /**
     * Deletes a channel from the list of favorites for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be deleted from favorites.
     * @return Boolean true if the operation is successful, false otherwise.
     * @throws NetworkException if there is a network-related issue during the operation.
     */
    @Throws(NetworkException::class)
    override suspend fun deleteFavoriteChannels(profileId: String, channelId: String): Boolean = safeNetworkCall {
        userService.deleteFavoriteChannels(profileId, channelId).code == DELETE_FAVORITE_CHANNEL_SUCCESSFULLY
    }
}