package com.dreamsoftware.tvnexa.data.di

import com.dreamsoftware.tvnexa.data.mapper.AuthResponseMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.AuthSessionPreferenceMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SignupUserMapperImpl
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.utils.IMapper
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Component(modules = [DataMapperComponent.DataMapperModule::class])
interface DataMapperComponent {

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataMapperModule {

        @Binds
        fun bindSignupUserMapper(impl: SignupUserMapperImpl): IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO>

        @Binds
        fun bindAuthResponseMapper(impl: AuthResponseMapperImpl): IOneSideMapper<AuthResponseDTO, AuthSessionBO>

        @Binds
        fun bindAuthSessionMapper(impl: AuthSessionPreferenceMapperImpl): IMapper<AuthSessionPreferenceDTO, AuthSessionBO>

    }

    fun authResponseNetworkMapper(): IOneSideMapper<AuthResponseDTO, AuthSessionBO>

    fun authSessionPreferenceMapper(): IMapper<AuthSessionPreferenceDTO, AuthSessionBO>

    fun signupUserMapper(): IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO>

}