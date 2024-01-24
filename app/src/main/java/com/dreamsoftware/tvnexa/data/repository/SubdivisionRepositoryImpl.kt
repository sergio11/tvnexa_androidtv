package com.dreamsoftware.tvnexa.data.repository

import com.dreamsoftware.tvnexa.data.network.datasource.ISubdivisionDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.SubdivisionResponseDTO
import com.dreamsoftware.tvnexa.data.repository.core.SupportRepositoryImpl
import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO
import com.dreamsoftware.tvnexa.domain.repository.ISubdivisionRepository
import com.dreamsoftware.tvnexa.utils.IOneSideMapper
import kotlin.jvm.Throws

internal class SubdivisionRepositoryImpl(
    private val subdivisionDataSource: ISubdivisionDataSource,
    private val subdivisionMapper: IOneSideMapper<SubdivisionResponseDTO, SubdivisionBO>
): SupportRepositoryImpl(), ISubdivisionRepository {

    @Throws(
        DomainException.NoSubdivisionFoundException::class,
        DomainException.InternalErrorException::class
    )
    override suspend fun findAll(): List<SubdivisionBO> = safeExecute {
        subdivisionDataSource.findAll().let(subdivisionMapper::mapInListToOutList).toList().also {
            if(it.isEmpty()) {
                throw DomainException.NoSubdivisionFoundException("No subdivision found")
            }
        }
    }
}