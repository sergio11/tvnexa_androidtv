package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO

/**
 * Interface for managing user-related data.
 */
interface IUserRepository {

    /**
     * Fetches the user details.
     * @return [UserDetailBO] containing user details.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getDetail(): UserDetailBO

    /**
     * Updates the user details.
     * @param data The data containing the updated user details.
     * @return [UserDetailBO] containing the updated user details.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO

    /**
     * Fetches the profiles associated with the user.
     * @return [List] of [ProfileBO] containing user profiles.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getProfiles(): List<ProfileBO>

    /**
     * Updates the profile details.
     * @param profileId The ID of the profile to be updated.
     * @param data The data containing the updated profile details.
     * @return [ProfileBO] containing the updated profile details.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestBO): ProfileBO

    /**
     * Deletes the profile associated with the given profile ID.
     * @param profileId The ID of the profile to be deleted.
     * @return `true` if the profile is successfully deleted, `false` otherwise.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun deleteProfile(profileId: String): Boolean

    /**
     * Creates a new profile with the provided data.
     * @param data The data containing the details of the profile to be created.
     * @return `true` if the profile is successfully created, `false` otherwise.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun createProfile(data: CreateProfileRequestBO): Boolean

    /**
     * Saves the selected profile to the session data.
     * @param profile The profile to be saved.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun selectProfile(profile: ProfileBO)

    /**
     * Retrieves the profile business object for the specified profile ID.
     *
     * @param profileId The ID of the profile to retrieve.
     * @return ProfileBO The business object representing the profile information.
     * @throws [DomainException.InternalErrorException] if there is an internal error during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getProfileById(profileId: String): ProfileBO

    /**
     * Retrieves the selected profile from the session data.
     * @return The selected profile.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getProfileSelected(): ProfileBO

    /**
     * Verifies the PIN associated with the given profile ID.
     * @param profileId The ID of the profile for PIN verification.
     * @param pin The PIN to be verified.
     * @return `true` if the PIN is verified successfully, `false` otherwise.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun verifyPin(profileId: String, pin: Int): Boolean

    /**
     * Retrieves the list of blocked channels associated with the given profile ID.
     * @param profileId The ID of the profile.
     * @return The list of blocked channels.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getBlockedChannels(profileId: String): List<SimpleChannelBO>

    /**
     * Retrieves the list of favorite channels associated with the given profile ID.
     * @param profileId The ID of the profile.
     * @return The list of favorite channels.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelBO>

    /**
     * Saves a channel as a favorite for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be saved as a favorite.
     * @throws DomainException.InternalErrorException if an internal error occurs during the operation.
     * @throws DomainException.SaveFavoriteChannelErrorException if an error occurs while saving the favorite channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.SaveFavoriteChannelErrorException::class
    )
    suspend fun saveFavoriteChannels(profileId: String, channelId: String)

    /**
     * Deletes a channel from the list of favorites for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be deleted from favorites.
     * @throws DomainException.InternalErrorException if an internal error occurs during the operation.
     * @throws DomainException.DeleteFavoriteChannelErrorException if an error occurs while deleting the favorite channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.DeleteFavoriteChannelErrorException::class
    )
    suspend fun deleteFavoriteChannels(profileId: String, channelId: String)
}