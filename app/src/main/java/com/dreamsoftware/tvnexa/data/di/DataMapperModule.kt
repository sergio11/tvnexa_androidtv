package com.dreamsoftware.tvnexa.data.di

import com.dreamsoftware.tvnexa.data.mapper.AuthResponseMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.AuthSessionPreferenceMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.CategoryMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ChannelDetailMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ChannelGuideMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.ChannelStreamMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.CountryMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.LanguageMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.RegionMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SignupUserMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SimpleChannelMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.SubdivisionMapperImpl
import com.dreamsoftware.tvnexa.data.mapper.UserDetailMapperImpl
import com.dreamsoftware.tvnexa.data.network.dto.request.SignUpUserNetworkDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.AuthResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelDetailResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelGuideResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.ChannelStreamResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.CountryResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.LanguageResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.RegionResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SimpleChannelResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.network.dto.response.UserResponseDTO
import com.dreamsoftware.tvnexa.data.preferences.dto.AuthSessionPreferenceDTO
import com.dreamsoftware.tvnexa.domain.model.AuthSessionBO
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import com.dreamsoftware.tvnexa.domain.model.ChannelDetailBO
import com.dreamsoftware.tvnexa.domain.model.ChannelGuideBO
import com.dreamsoftware.tvnexa.domain.model.ChannelStreamBO
import com.dreamsoftware.tvnexa.domain.model.CountryBO
import com.dreamsoftware.tvnexa.domain.model.LanguageBO
import com.dreamsoftware.tvnexa.domain.model.RegionBO
import com.dreamsoftware.tvnexa.domain.model.SaveUserBO
import com.dreamsoftware.tvnexa.domain.model.SimpleChannelBO
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
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
        fun bindAuthResponseMapper(impl: AuthResponseMapperImpl): IOneSideMapper<AuthResponseDTO, AuthSessionBO>

        @Binds
        fun bindAuthSessionMapper(impl: AuthSessionPreferenceMapperImpl): IMapper<AuthSessionPreferenceDTO, AuthSessionBO>

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
    }

    fun authResponseNetworkMapper(): IOneSideMapper<AuthResponseDTO, AuthSessionBO>

    fun authSessionPreferenceMapper(): IMapper<AuthSessionPreferenceDTO, AuthSessionBO>

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
}