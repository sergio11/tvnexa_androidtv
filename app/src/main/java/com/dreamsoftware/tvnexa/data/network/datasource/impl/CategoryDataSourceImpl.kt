package com.dreamsoftware.tvnexa.data.network.datasource.impl

import com.dreamsoftware.tvnexa.data.network.core.SupportNetworkDataSource
import com.dreamsoftware.tvnexa.data.network.datasource.ICategoryDataSource
import com.dreamsoftware.tvnexa.data.network.dto.response.CategoryResponseDTO
import com.dreamsoftware.tvnexa.data.network.exception.NetworkException
import com.dreamsoftware.tvnexa.data.network.service.ICategoryService

internal class CategoryDataSourceImpl(
    private val categoryService: ICategoryService
): SupportNetworkDataSource(), ICategoryDataSource {

    @Throws(NetworkException::class)
    override suspend fun findAll(): List<CategoryResponseDTO> = safeNetworkCall {
        categoryService.all().data
    }
}