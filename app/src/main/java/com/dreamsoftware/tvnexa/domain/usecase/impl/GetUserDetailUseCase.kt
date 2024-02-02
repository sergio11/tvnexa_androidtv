package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetUserDetailUseCase(
    private val userRepository: IUserRepository
): BaseUseCase<UserDetailBO>() {
    override suspend fun onExecuted(): UserDetailBO =
        userRepository.getDetail()
}