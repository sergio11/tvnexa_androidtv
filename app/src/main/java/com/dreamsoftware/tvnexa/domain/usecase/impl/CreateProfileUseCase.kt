package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.extensions.isProfileAliasNotValid
import com.dreamsoftware.tvnexa.domain.extensions.isSecurePinNotValid
import com.dreamsoftware.tvnexa.domain.model.AvatarTypeEnum
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.FormFieldKey
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams
import com.dreamsoftware.tvnexa.utils.combinedLet

class CreateProfileUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<CreateProfileUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean =
        validateData(params)?.let {
            throw DomainException.InvalidDataException("Invalid profile data",
                field = it.first, value = it.second)
        } ?: with(params) {
            combinedLet(avatarType, pin?.toIntOrNull()) { type, pin ->
                userRepository.createProfile(CreateProfileRequestBO(
                    alias = alias,
                    pin = pin,
                    enableNSFW = enableNSFW,
                    avatarType = type
                ))
            } ?: false
        }

    private fun validateData(params: Params): Pair<FormFieldKey, String?>? = with(params) {
        when {
            alias.isProfileAliasNotValid() -> FormFieldKey.PROFILE_ALIAS to alias
            pin.isSecurePinNotValid() -> FormFieldKey.SECURE_PIN to pin.toString()
            avatarType == null -> FormFieldKey.AVATAR_TYPE to null
            else -> null
        }
    }

    data class Params(
        val alias: String,
        val pin: String?,
        val enableNSFW: Boolean,
        val avatarType: AvatarTypeEnum?
    )
}