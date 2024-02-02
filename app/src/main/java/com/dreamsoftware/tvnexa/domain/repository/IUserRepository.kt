package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.PinVerificationRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO

interface IUserRepository {

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun getDetail(): UserDetailBO

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun updateDetail(data: UpdatedUserRequestBO): UserDetailBO

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun getProfiles(): List<ProfileBO>

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun updateProfile(profileId: String, data: UpdatedProfileRequestBO): ProfileBO

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun deleteProfile(profileId: String): Boolean

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun createProfile(data: CreateProfileRequestBO): Boolean

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun verifyPin(profileId: String, pin: Int): Boolean

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun getBlockedChannels(profileId: String): List<SimpleChannelBO>

    @Throws(
        DomainException.InternalErrorException::class
    )
    suspend fun getFavoriteChannels(profileId: String): List<SimpleChannelBO>
}