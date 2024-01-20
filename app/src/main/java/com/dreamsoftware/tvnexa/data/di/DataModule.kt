package com.dreamsoftware.tvnexa.data.di

import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.data.repository.AuthRepositoryImpl
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.utils.IMapper
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSource: IAuthRemoteDataSource,
        authSessionDataSource: IAuthSessionDataSource,
        signupUserBOMapper: IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO>,
        authResponseMapper: IOneSideMapper<AuthResponseDTO, AuthSessionBO>,
        authSessionBOMapper: IMapper<AuthSessionPreferenceDTO, AuthSessionBO>
    ): IAuthRepository = AuthRepositoryImpl(
        authRemoteDataSource,
        authSessionDataSource,
        signupUserBOMapper,
        authResponseMapper,
        authSessionBOMapper
    )
}