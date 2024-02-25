package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.extensions.isProfileAliasNotValid
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class UpdateProfileUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<UpdateProfileUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO =
        validateData(params)?.let {
            throw DomainException.InvalidDataException("Invalid profile data",
                field = it.first, value = it.second)
        } ?: with(params) {
            userRepository.updateProfile(profileId, UpdatedProfileRequestBO(
                alias = alias,
                enableNSFW = enableNSFW,
                avatarType = avatarType
            ))
        }

    private fun validateData(params: Params): Pair<FormFieldKey, String>? = with(params) {
        if(alias != null && alias.isProfileAliasNotValid()) {
            FormFieldKey.PROFILE_ALIAS to alias
        } else {
            null
        }
    }

    data class Params(
        val profileId: String,
        val alias: String?,
        val enableNSFW: Boolean?,
        val avatarType: String?
    )
}