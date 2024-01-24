package com.dreamsoftware.tvnexa.domain.repository

import com.dreamsoftware.tvnexa.domain.model.SubdivisionBO

interface ISubdivisionRepository {

    suspend fun findAll(): List<SubdivisionBO>
}