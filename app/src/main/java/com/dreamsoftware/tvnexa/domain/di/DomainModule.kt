package com.dreamsoftware.tvnexa.domain.di

import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetCategoriesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetCountriesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetSessionUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignInUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignUpUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyUserSessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(repository: IAuthRepository): SignInUseCase =
        SignInUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(repository: IAuthRepository): SignUpUseCase =
        SignUpUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetSessionUseCase(repository: IAuthRepository): GetSessionUseCase =
        GetSessionUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideVerifyUserSessionUseCase(repository: IAuthRepository): VerifyUserSessionUseCase =
        VerifyUserSessionUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetCountriesUseCase(repository: ICountryRepository): GetCountriesUseCase =
        GetCountriesUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(repository: ICategoryRepository): GetCategoriesUseCase =
        GetCategoriesUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetChannelsUseCase(repository: IChannelRepository): GetChannelsUseCase =
        GetChannelsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetChannelDetailUseCase(repository: IChannelRepository): GetChannelDetailUseCase =
        GetChannelDetailUseCase(repository)
}