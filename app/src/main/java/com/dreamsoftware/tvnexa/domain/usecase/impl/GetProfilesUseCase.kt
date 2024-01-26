package com.dreamsoftware.tvnexa.domain.usecase.impl

import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.ProfileTypeEnum
import com.dreamsoftware.tvnexa.domain.usecase.core.BaseUseCase

class GetProfilesUseCase: BaseUseCase<List<ProfileBO>>() {
    override suspend fun onExecuted(): List<ProfileBO> = buildList {
        add(ProfileBO(
            uuid = "1234-5678-1234-5432",
            name = "Sergio",
            type = ProfileTypeEnum.BOY
        ))
        add(ProfileBO(
            uuid = "1234-5678-1234-5432",
            name = "Marta",
            type = ProfileTypeEnum.GIRL
        ))
        add(ProfileBO(
            uuid = "1234-5678-1234-5432",
            name = "Teresa",
            type = ProfileTypeEnum.WOMAN
        ))
        add(ProfileBO(
            uuid = "1234-5678-1234-5432",
            name = "Antonio",
            type = ProfileTypeEnum.MAN
        ))
    }
}