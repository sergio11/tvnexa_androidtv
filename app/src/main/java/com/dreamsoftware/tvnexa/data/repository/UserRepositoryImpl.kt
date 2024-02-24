package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.IUserDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.datasource.IProfileSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.utils.IMapper
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

/**
 * Implementation of [IUserRepository] that handles user-related data operations.
 * This repository interacts with the data sources and mappers to provide user-related functionalities.
 * @param userDataSource The data source responsible for fetching user-related data from the network.
 * @param userMapper Mapper to convert [UserResponseDTO] to [UserDetailBO].
 * @param updateUserMapper Mapper to convert [UpdatedUserRequestBO] to [UpdatedUserRequestDTO].
 * @param profileMapper Mapper to convert [ProfileResponseDTO] to [ProfileBO].
 * @param updateProfileMapper Mapper to convert [UpdatedProfileRequestBO] to [UpdatedProfileRequestDTO].
 * @param createProfileMapper Mapper to convert [CreateProfileRequestBO] to [CreateProfileRequestDTO].
 * @param channelMapper Mapper to convert [SimpleChannelResponseDTO] to [SimpleChannelBO].
 * @param profileSessionDataSource Data source responsible for managing profile session data in preferences.
 * @param profileSessionMapper Mapper to convert [ProfileBO] to [ProfileSelectedPreferenceDTO].
 */
internal class UserRepositoryImpl(
    private val userDataSource: IUserDataSource,
    private val userMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
    private val updateUserMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
    private val profileMapper: IOneSideMapper<ProfileResponseDTO, ProfileBO>,
    private val updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
    private val createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
    private val channelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>,
    private val profileSessionDataSource: IProfileSessionDataSource,
    private val profileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO>
): SupportRepositoryImpl(), IUserRepository {

    /**
     * Fetches the user details.
     * @return [UserDetailBO] containing user details.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getDetail(): UserDetailBO = safeExecute {
        userDataSource.getDetail().let(userMapper::mapInToOut)
    }

    /**
     * Updates the user details.
     * @param data The data containing the updated user details.
     * @return [UserDetailBO] containing the updated user details.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO = safeExecute {
        userDataSource.updateDetail(updateUserMapper.mapInToOut(data))
            .let(userMapper::mapInToOut)
    }

    /**
     * Fetches the profiles associated with the user.
     * @return [List] of [ProfileBO] containing user profiles.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getProfiles(): List<ProfileBO> = safeExecute {
        userDataSource.getProfiles()
            .let(profileMapper::mapInListToOutList)
            .toList()
    }

    /**
     * Updates the profile details.
     * @param profileId The ID of the profile to be updated.
     * @param data The data containing the updated profile details.
     * @return [ProfileBO] containing the updated profile details.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun updateProfile(
        profileId: String,
        data: UpdatedProfileRequestBO
    ): ProfileBO = safeExecute {
        userDataSource.updateProfile(profileId, updateProfileMapper.mapInToOut(data))
            .let(profileMapper::mapInToOut)
    }

    /**
     * Deletes the profile associated with the given profile ID.
     * @param profileId The ID of the profile to be deleted.
     * @return `true` if the profile is successfully deleted, `false` otherwise.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun deleteProfile(profileId: String): Boolean = safeExecute {
        userDataSource.deleteProfile(profileId)
    }

    /**
     * Creates a new profile with the provided data.
     * @param data The data containing the details of the profile to be created.
     * @return `true` if the profile is successfully created, `false` otherwise.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun createProfile(data: CreateProfileRequestBO): Boolean = safeExecute {
        userDataSource.createProfile(createProfileMapper.mapInToOut(data))
    }

    /**
     * Saves the selected profile to the session data.
     * @param profile The profile to be saved.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun selectProfile(profile: ProfileBO): Unit = safeExecute {
        profileSessionDataSource.save(profileSessionMapper.mapInToOut(profile))
    }

    /**
     * Retrieves the profile business object for the specified profile ID.
     *
     * @param profileId The ID of the profile to retrieve.
     * @return ProfileBO The business object representing the profile information.
     * @throws [DomainException.InternalErrorException] if there is an internal error during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun getProfileById(profileId: String): ProfileBO = safeExecute {
        userDataSource.getProfileById(profileId).let(profileMapper::mapInToOut)
    }

    /**
     * Retrieves the selected profile from the session data.
     * @return The selected profile.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun getProfileSelected(): ProfileBO = safeExecute {
        profileSessionMapper.mapOutToIn(profileSessionDataSource.get())
    }

    /**
     * Verifies the PIN associated with the given profile ID.
     * @param profileId The ID of the profile for PIN verification.
     * @param pin The PIN to be verified.
     * @return `true` if the PIN is verified successfully, `false` otherwise.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun verifyPin(profileId: String, pin: Int): Boolean = safeExecute {
        userDataSource.verifyPin(profileId, PinVerificationRequestDTO(pin))
    }

    /**
     * Retrieves the list of blocked channels associated with the given profile ID.
     * @param profileId The ID of the profile.
     * @return The list of blocked channels.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun getBlockedChannels(profileId: String): List<SimpleChannelBO> = safeExecute {
        userDataSource.getBlockedChannels(profileId)
            .let(channelMapper::mapInListToOutList)
            .toList()
    }

    /**
     * Retrieves the list of favorite channels associated with the given profile ID.
     * @param profileId The ID of the profile.
     * @return The list of favorite channels.
     * @throws [DomainException.InternalErrorException] if an error occurs during the operation.
     */
    @Throws(DomainException.InternalErrorException::class)
    override suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelBO> = safeExecute {
        userDataSource.getFavoriteChannels(profileId)
            .let(channelMapper::mapInListToOutList)
            .toList()
    }

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
    override suspend fun saveFavoriteChannels(profileId: String, channelId: String) {
        safeExecute {
            userDataSource.saveFavoriteChannels(profileId, channelId).let { isSuccess ->
                if (!isSuccess) {
                    throw DomainException.SaveFavoriteChannelErrorException("Channel $channelId could not be saved as favorite")
                }
            }
        }
    }

    /**
     * Deletes a channel from the list of favorites for the specified profile.
     *
     * @param profileId The ID of the profile.
     * @param channelId The ID of the channel to be deleted from favorites.
     * @return true if the operation is successful, false otherwise.
     * @throws DomainException.InternalErrorException if an internal error occurs during the operation.
     * @throws DomainException.DeleteFavoriteChannelErrorException if an error occurs while deleting the favorite channel.
     */
    @Throws(
        DomainException.InternalErrorException::class,
        DomainException.DeleteFavoriteChannelErrorException::class
    )
    override suspend fun deleteFavoriteChannels(profileId: String, channelId: String) {
        safeExecute {
            userDataSource.deleteFavoriteChannels(profileId, channelId).let { isSuccess ->
                if (!isSuccess) {
                    throw DomainException.DeleteFavoriteChannelErrorException("Channel $channelId could not be deleted from favorites")
                }
            }
        }
    }
}