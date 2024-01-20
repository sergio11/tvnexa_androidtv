package com.dreamsoftware.tvnexa.domain.di

import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.usecase.impl.GetSessionUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignInUseCase
import com.dreamsoftware.tvnexa.domain.usecase.impl.SignUpUseCase
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
}