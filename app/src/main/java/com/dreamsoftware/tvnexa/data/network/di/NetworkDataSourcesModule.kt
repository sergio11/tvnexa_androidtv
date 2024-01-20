package com.dreamsoftware.tvnexa.data.network.di

import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.service.IAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkDataSourcesModule {

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(authService: IAuthService): IAuthRemoteDataSource =
        AuthRemoteDataSourceImpl(authService)
}