package com.dreamsoftware.tvnexa.data.mapper

import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import javax.inject.Inject

class SignupUserMapperImpl @Inject constructor(): IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO> {

    override fun mapInToOut(input: SaveUserBO): SignUpUserNetworkDTO = with(input) {
        SignUpUserNetworkDTO(
            username = username,
            password = password,
            email = email,
            firstName = firstName,
            lastName = lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<SaveUserBO>): Iterable<SignUpUserNetworkDTO> =
        input.map(::mapInToOut)
}