package com.example.feature_details.domain

import com.example.core.domain.entity.Launch
import com.example.core.domain.mapper.Either
import com.example.feature_details.domain.entity.Crew

interface DetailsRepository {
    suspend fun fetchLaunchById(id: String): Either<Launch>
    suspend fun fetchCrew(): Either<List<Crew>>
}
