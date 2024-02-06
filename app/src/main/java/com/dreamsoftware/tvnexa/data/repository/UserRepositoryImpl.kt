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

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getDetail(): UserDetailBO = safeExecute {
        userDataSource.getDetail().let(userMapper::mapInToOut)
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO = safeExecute {
        userDataSource.updateDetail(updateUserMapper.mapInToOut(data))
            .let(userMapper::mapInToOut)
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getProfiles(): List<ProfileBO> = safeExecute {
        userDataSource.getProfiles()
            .let(profileMapper::mapInListToOutList)
            .toList()
    }

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

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun deleteProfile(profileId: String): Boolean = safeExecute {
        userDataSource.deleteProfile(profileId)
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun createProfile(data: CreateProfileRequestBO): Boolean = safeExecute {
        userDataSource.createProfile(createProfileMapper.mapInToOut(data))
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun selectProfile(profile: ProfileBO): Unit = safeExecute {
        profileSessionDataSource.save(profileSessionMapper.mapInToOut(profile))
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getProfileSelected(): ProfileBO = safeExecute {
        profileSessionMapper.mapOutToIn(profileSessionDataSource.get())
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun verifyPin(profileId: String, pin: Int): Boolean = safeExecute {
        userDataSource.verifyPin(profileId, PinVerificationRequestDTO(pin))
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getBlockedChannels(profileId: String): List<SimpleChannelBO> = safeExecute {
        userDataSource.getBlockedChannels(profileId)
            .let(channelMapper::mapInListToOutList)
            .toList()
    }

    @Throws(
        DomainException.InternalErrorException::class
    )
    override suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelBO> = safeExecute {
        userDataSource.getFavoriteChannels(profileId)
            .let(channelMapper::mapInListToOutList)
            .toList()
    }
}