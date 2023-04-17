package com.example.feature_details.domain

import javax.inject.Inject

class DetailsInteractor @Inject constructor(private val detailsRepository: DetailsRepository)  {
    suspend fun fetchLaunch(launchId: String) = detailsRepository.fetchLaunchById(launchId)
    suspend fun fetchCrew() = detailsRepository.fetchCrew()
}
