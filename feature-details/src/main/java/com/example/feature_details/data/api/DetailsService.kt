package com.example.feature_details.data.api

import com.example.core.data.response.common.LaunchResponse
import com.example.feature_details.data.response.CrewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {
    @GET("launches/{id}")
    suspend fun fetchLaunchById(@Path("id") launchId: String): Response<LaunchResponse>

    @GET("crew")
    suspend fun fetchCrew(): Response<CrewsResponse>
}
