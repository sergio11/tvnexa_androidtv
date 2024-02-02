package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCaseWithParams

class UpdateUserDetailUseCase(
    private val userRepository: IUserRepository
): BaseUseCaseWithParams<UpdateUserDetailUseCase.Params, UserDetailBO>() {

    override suspend fun onExecuted(params: Params): UserDetailBO = with(params) {
        userRepository.updateDetail(UpdatedUserRequestBO(firstName, lastName, username))
    }

    data class Params(
        val firstName: String?,
        val lastName: String?,
        val username: String?
    )
}