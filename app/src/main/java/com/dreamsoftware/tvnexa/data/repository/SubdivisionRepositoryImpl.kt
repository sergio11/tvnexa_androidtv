package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ISubdivisionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.domain.repository.ISubdivisionRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SubdivisionRepositoryImpl(
    private val subdivisionDataSource: ISubdivisionDataSource,
    private val subdivisionMapper: IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>
): ISubdivisionRepository {
    override suspend fun findAll(): List<SubdivisionBO> = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }
}