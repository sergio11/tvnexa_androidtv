package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.model.CategoryBO

interface ICategoryRepository {

    suspend fun findAll(): List<CategoryBO>
}