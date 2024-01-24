package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.exception.DomainException
import com.dreamsoftware.tvnexa.domain.model.CategoryBO
import kotlin.jvm.Throws

interface ICategoryRepository {

    @Throws(
        DomainException.NoCategoriesFoundException::class,
        DomainException.InternalErrorException::class
    )
    suspend fun findAll(): List<CategoryBO>
}