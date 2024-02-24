package com.dreamsoftware.tvnexa.domain.di

import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileByIdUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.CreateProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.DeleteProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetBlockedChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetCategoriesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetCountriesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetFavoriteChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfileSelectedUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetProfilesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetUserDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.HasMultiplesProfilesUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SearchChannelsUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SelectProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignInUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignOffUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignUpUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.UpdateProfileUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.UpdateUserDetailUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyPinUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.VerifyUserSessionUseCase
import com.dreamsoftware.tvnexa.utils.AppEventBus
import com.dreamsoftware.tvnexa.utils.ISessionAware
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
    fun provideSignInUseCase(repository: IAuthRepository, sessionAware: ISessionAware): SignInUseCase =
        SignInUseCase(repository, sessionAware)

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(repository: IAuthRepository): SignUpUseCase =
        SignUpUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideVerifyUserSessionUseCase(repository: IAuthRepository, sessionAware: ISessionAware): VerifyUserSessionUseCase =
        VerifyUserSessionUseCase(repository, sessionAware)

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

    @Provides
    @ViewModelScoped
    fun provideGetProfilesUseCase(repository: IUserRepository): GetProfilesUseCase =
        GetProfilesUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetUserDetailUseCase(repository: IUserRepository): GetUserDetailUseCase =
        GetUserDetailUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideDeleteProfileUseCase(repository: IUserRepository): DeleteProfileUseCase =
        DeleteProfileUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideUpdateProfileUseCase(repository: IUserRepository): UpdateProfileUseCase =
        UpdateProfileUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideUpdateUserDetailUseCase(repository: IUserRepository): UpdateUserDetailUseCase =
        UpdateUserDetailUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideCreateProfileUseCase(repository: IUserRepository): CreateProfileUseCase =
        CreateProfileUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetBlockedChannelsUseCase(repository: IUserRepository): GetBlockedChannelsUseCase =
        GetBlockedChannelsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetFavoriteChannelsUseCase(repository: IUserRepository): GetFavoriteChannelsUseCase =
        GetFavoriteChannelsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideVerifyPinUseCase(repository: IUserRepository): VerifyPinUseCase =
        VerifyPinUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSearchChannelsUseCase(repository: IChannelRepository): SearchChannelsUseCase =
        SearchChannelsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSelectProfileUseCase(repository: IUserRepository): SelectProfileUseCase =
        SelectProfileUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideHasMultiplesProfilesUseCase(repository: IUserRepository): HasMultiplesProfilesUseCase =
        HasMultiplesProfilesUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetProfileSelectedUseCase(repository: IUserRepository): GetProfileSelectedUseCase =
        GetProfileSelectedUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideGetProfileByIdUseCase(repository: IUserRepository): GetProfileByIdUseCase =
        GetProfileByIdUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideSignOffUseCase(
        repository: IAuthRepository,
        sessionAware: ISessionAware,
        appEventBus: AppEventBus
    ): SignOffUseCase = SignOffUseCase(repository, sessionAware, appEventBus)
}