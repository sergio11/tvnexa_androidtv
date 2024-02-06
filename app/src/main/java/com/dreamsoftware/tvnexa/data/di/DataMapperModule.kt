package com.dreamsoftware.tvnexa.data.di

import com.dreamsoftware.tvnexa.data.mapper.AuthenticationResponseMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ReadAuthSessionDataMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.CategoryMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ChannelDetailMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ChannelGuideMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ChannelStreamMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.CountryMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.CreateProfileMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.LanguageMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ProfileMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ProfileSessionMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.RegionMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SaveAuthSessionDataMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SignupUserMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SimpleChannelMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SubdivisionMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.UpdateProfileMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.UpdateUserMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.UserDetailMapperImpl
import com.dreamsoftware.tvnexa.data.network.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedProfileRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelGuideResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelStreamResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.LanguageResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ProfileResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.AuthenticationBO
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.ChannelGuideBO
import com.dreamsoftware.tvnexa.domain.model.ChannelStreamBO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.CreateProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.LanguageBO
import com.dreamsoftware.tvnexa.domain.model.ProfileBO
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.tvnexa.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.tvnexa.domain.model.UserDetailBO
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
        fun bindAuthResponseMapper(impl: AuthenticationResponseMapperImpl): IOneSideMapper<AuthResponseDTO, AuthenticationBO>

        @Binds
        fun bindReadAuthSessionDataMapper(impl: ReadAuthSessionDataMapperImpl): IOneSideMapper<AuthSessionPreferenceDTO, AuthSessionBO>

        @Binds
        fun bindSaveAuthSessionDataMapper(impl: SaveAuthSessionDataMapperImpl): IOneSideMapper<AuthenticationBO, AuthSessionPreferenceDTO>

        @Binds
        fun bindCategoryMapper(impl: CategoryMapperImpl): IOneSideMapper<CategoryResponseDTO, CategoryBO>

        @Binds
        fun bindLanguageMapper(impl: LanguageMapperImpl): IOneSideMapper<LanguageResponseDTO, LanguageBO>

        @Binds
        fun bindSubdivisionMapper(impl: SubdivisionMapperImpl): IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>

        @Binds
        fun bindRegionMapper(impl: RegionMapperImpl): IOneSideMapper<RegionResponseDTO, RegionBO>

        @Binds
        fun bindCountryMapper(impl: CountryMapperImpl): IOneSideMapper<CountryResponseDTO, CountryBO>

        @Binds
        fun bindUserDetailMapper(impl: UserDetailMapperImpl): IOneSideMapper<UserResponseDTO, UserDetailBO>

        @Binds
        fun bindSimpleChannelMapper(impl: SimpleChannelMapperImpl): IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>

        @Binds
        fun bindChannelGuideMapper(impl: ChannelGuideMapperImpl): IOneSideMapper<ChannelGuideResponseDTO, ChannelGuideBO>

        @Binds
        fun bindChannelStreamMapper(impl: ChannelStreamMapperImpl): IOneSideMapper<ChannelStreamResponseDTO, ChannelStreamBO>

        @Binds
        fun bindChannelDetailMapper(impl: ChannelDetailMapperImpl): IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>

        @Binds
        fun bindProfileMapperImpl(impl: ProfileMapperImpl): IOneSideMapper<ProfileResponseDTO, ProfileBO>

        @Binds
        fun bindUpdateUserMapperImpl(impl: UpdateUserMapperImpl): IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>

        @Binds
        fun bindUpdateProfileMapperImpl(impl: UpdateProfileMapperImpl): IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>

        @Binds
        fun bindCreateProfileMapperImpl(impl: CreateProfileMapperImpl): IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>

        @Binds
        fun bindProfileSessionMapperImpl(impl: ProfileSessionMapperImpl): IMapper<ProfileBO, ProfileSelectedPreferenceDTO>
    }

    fun authResponseNetworkMapper(): IOneSideMapper<AuthResponseDTO, AuthenticationBO>

    fun readAuthSessionDataMapper(): IOneSideMapper<AuthSessionPreferenceDTO, AuthSessionBO>

    fun saveAuthSessionDataMapper(): IOneSideMapper<AuthenticationBO, AuthSessionPreferenceDTO>

    fun signupUserMapper(): IOneSideMapper<SaveUserBO, SignUpUserNetworkDTO>

    fun categoryMapper(): IOneSideMapper<CategoryResponseDTO, CategoryBO>

    fun languageMapper(): IOneSideMapper<LanguageResponseDTO, LanguageBO>

    fun subdivisionMapper(): IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>

    fun regionMapper(): IOneSideMapper<RegionResponseDTO, RegionBO>

    fun countryMapper(): IOneSideMapper<CountryResponseDTO, CountryBO>

    fun userDetailMapper(): IOneSideMapper<UserResponseDTO, UserDetailBO>

    fun simpleChannelMapper(): IOneSideMapper<SimpleChannelResponseDTO, SimpleChannelBO>

    fun channelGuideMapper(): IOneSideMapper<ChannelGuideResponseDTO, ChannelGuideBO>

    fun channelStreamMapper(): IOneSideMapper<ChannelStreamResponseDTO, ChannelStreamBO>

    fun channelDetailMapper(): IOneSideMapper<ChannelDetailResponseDTO, ChannelDetailBO>

    fun profileMapper(): IOneSideMapper<ProfileResponseDTO, ProfileBO>

    fun updateUserMapper(): IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO>

    fun updateProfileMapper(): IOneSideMapper<UpdatedProfileRequestBO, UpdatedProfileRequestDTO>

    fun createProfileMapper(): IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO>

    fun profileSessionMapper(): IMapper<ProfileBO, ProfileSelectedPreferenceDTO>
}