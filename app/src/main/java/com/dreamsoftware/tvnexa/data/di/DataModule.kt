package com.dreamsoftware.tvnexa.data.di

import com.dreamsoftware.tvnexa.data.network.datasource.IAuthRemoteDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ICategoryDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IChannelsDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ICountryDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IEpgDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IRegionDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ISubdivisionDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.IUserDataSource
import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.EpgChannelProgrammeResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.datasource.IAuthSessionDataSource
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.data.repository.AuthRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.CategoryRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.ChannelRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.CountryRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.EpgRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.RegionRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.SubdivisionRepositoryImpl
import com.dreamsoftware.tvnexa.data.repository.UserRepositoryImpl
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.EpgChannelProgrammeBO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
import com.dreamsoftware.tvnexa.domain.repository.IAuthRepository
import com.dreamsoftware.tvnexa.domain.repository.ICategoryRepository
import com.dreamsoftware.tvnexa.domain.repository.IChannelRepository
import com.dreamsoftware.tvnexa.domain.repository.ICountryRepository
import com.dreamsoftware.tvnexa.domain.repository.IEpgRepository
import com.dreamsoftware.tvnexa.domain.repository.IRegionRepository
import com.dreamsoftware.tvnexa.domain.repository.ISubdivisionRepository
import com.dreamsoftware.tvnexa.domain.repository.IUserRepository
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
        authResponseMapper: IOneSideMapper<AuthResponseDTO, AuthenticationBO>,
        readAuthSessionDataBOMapper: IOneSideMapper<AuthSessionPreferenceDTO, AuthSessionBO>,
        saveAuthSessionDataBOMapper: IOneSideMapper<AuthenticationBO, AuthSessionPreferenceDTO>
    ): IAuthRepository = AuthRepositoryImpl(
        authRemoteDataSource,
        authSessionDataSource,
        signupUserBOMapper,
        authResponseMapper,
        readAuthSessionDataBOMapper,
        saveAuthSessionDataBOMapper
    )

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryDataSource: ICategoryDataSource,
        categoryMapper: IOneSideMapper<CategoryResponseDTO, CategoryBO>
    ): ICategoryRepository = CategoryRepositoryImpl(
        categoryDataSource = categoryDataSource,
        categoryMapper = categoryMapper
    )

    @Provides
    @Singleton
    fun provideCountryRepository(
        countryDataSource: ICountryDataSource,
        countryMapper: IOneSideMapper<CountryResponseDTO, CountryBO>
    ): ICountryRepository = CountryRepositoryImpl(
        countryDataSource = countryDataSource,
        countryMapper = countryMapper
    )

    @Provides
    @Singleton
    fun provideRegionRepository(
        regionDataSource: IRegionDataSource,
        regionMapper: IOneSideMapper<RegionResponseDTO, RegionBO>
    ): IRegionRepository = RegionRepositoryImpl(
        regionDataSource = regionDataSource,
        regionMapper = regionMapper
    )


    @Provides
    @Singleton
    fun provideSubdivisionRepository(
        subdivisionDataSource: ISubdivisionDataSource,
        subdivisionMapper: IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>
    ): ISubdivisionRepository = SubdivisionRepositoryImpl(
        subdivisionDataSource = subdivisionDataSource,
        subdivisionMapper = subdivisionMapper
    )


    @Provides
    @Singleton
    fun provideChannelRepository(
        channelsDataSource: IChannelsDataSource,
        simpleChannelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>,
        channelDetailMapper: IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>
    ): IChannelRepository = ChannelRepositoryImpl(
        channelsDataSource = channelsDataSource,
        simpleChannelMapper = simpleChannelMapper,
        channelMapper = channelDetailMapper
    )

    @Provides
    @Singleton
    fun provideEpgRepository(
        epgDataSource: IEpgDataSource,
        epgMapper: IOneSideMapper<EpgChannelProgrammeResponseDTO, EpgChannelProgrammeBO>
    ): IEpgRepository = EpgRepositoryImpl(
        epgDataSource = epgDataSource,
        epgMapper = epgMapper
    )

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataSource: IUserDataSource,
        userMapper: IOneSideMapper<UserResponseDTO, UserDetailBO>,
        updateUserMapper: IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>,
        profileMapper: IOneSideMapper<ProfileResponseDTO, ProfileBO>,
        updateProfileMapper: IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>,
        createProfileMapper: IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>,
        channelMapper: IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>
    ): IUserRepository = UserRepositoryImpl(
        userDataSource = userDataSource,
        updateUserMapper = updateUserMapper,
        userMapper = userMapper,
        profileMapper = profileMapper,
        updateProfileMapper = updateProfileMapper,
        createProfileMapper = createProfileMapper,
        channelMapper = channelMapper
    )
}