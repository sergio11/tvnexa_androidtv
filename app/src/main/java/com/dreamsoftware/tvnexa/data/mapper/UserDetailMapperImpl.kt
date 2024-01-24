package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class UserDetailMapperImpl @Inject constructor(): IOneSideMapper<UserResponseDTO, UserDetailBO> {

    override fun mapInToOut(input: UserResponseDTO): UserDetailBO = with(input) {
        UserDetailBO(
            uuid = uuid,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<UserResponseDTO>): Iterable<UserDetailBO> =
        input.map(::mapInToOut)
}