package com.dreamsoftware.tvnexa.data.network.di

import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ICategoryDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IChannelsDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ICountryDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IEpgDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IRegionDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ISubdivisionDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IUserDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.impl.AuthRemoteDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.CategoryDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.ChannelDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.CountryDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.EpgDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.RegionDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.SubdivisionDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.datasource.impl.UserDataSourceImpl
import com.dreamsoftware.tvnexa.data.network.service.IAuthService
import com.dreamsoftware.tvnexa.data.network.service.ICategoryService
import com.dreamsoftware.tvnexa.data.network.service.IChannelsService
import com.dreamsoftware.tvnexa.data.network.service.ICountryService
import com.dreamsoftware.tvnexa.data.network.service.IEpgService
import com.dreamsoftware.tvnexa.data.network.service.IRegionService
import com.dreamsoftware.tvnexa.data.network.service.ISubdivisionService
import com.dreamsoftware.tvnexa.data.network.service.IUserService
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

    @Provides
    @Singleton
    fun provideCategoryDataSource(categoryService: ICategoryService): ICategoryDataSource =
        CategoryDataSourceImpl(categoryService)

    @Provides
    @Singleton
    fun provideChannelDataSource(channelService: IChannelsService): IChannelsDataSource =
        ChannelDataSourceImpl(channelService)

    @Provides
    @Singleton
    fun provideCountryDataSource(countryService: ICountryService): ICountryDataSource =
        CountryDataSourceImpl(countryService)

    @Provides
    @Singleton
    fun provideRegionDataSource(regionService: IRegionService): IRegionDataSource =
        RegionDataSourceImpl(regionService)

    @Provides
    @Singleton
    fun provideSubdivisionDataSource(subdivisionService: ISubdivisionService): ISubdivisionDataSource =
        SubdivisionDataSourceImpl(subdivisionService)

    @Provides
    @Singleton
    fun provideEpgDataSource(epgService: IEpgService): IEpgDataSource =
        EpgDataSourceImpl(epgService)

    @Provides
    @Singleton
    fun provideUserDataSource(userService: IUserService): IUserDataSource =
        UserDataSourceImpl(userService)
}