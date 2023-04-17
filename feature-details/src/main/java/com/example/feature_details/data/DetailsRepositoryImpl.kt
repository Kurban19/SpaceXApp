package com.example.feature_details.data

import com.example.core.data.toEither
import com.example.core.domain.entity.Launch
import com.example.core.domain.mapper.Either
import com.example.feature_details.data.api.DetailsService
import com.example.feature_details.data.mapper.CrewResponseMapper
import com.example.feature_details.data.mapper.LaunchResponseMapper
import com.example.feature_details.domain.DetailsRepository
import com.example.feature_details.domain.entity.Crew

class DetailsRepositoryImpl(private val detailsService: DetailsService): DetailsRepository {
    override suspend fun fetchLaunchById(id: String): Either<Launch> =
        detailsService.fetchLaunchById(id).toEither(LaunchResponseMapper())

    override suspend fun fetchCrew(): Either<List<Crew>> =
        detailsService.fetchCrew().toEither(CrewResponseMapper())
}