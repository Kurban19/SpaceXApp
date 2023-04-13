package com.example.feature_main.domain

import javax.inject.Inject

class MainInteractor @Inject constructor(private val mainRepository: MainRepository) {

    suspend fun fetchLaunches() = mainRepository.getLaunches()

}
