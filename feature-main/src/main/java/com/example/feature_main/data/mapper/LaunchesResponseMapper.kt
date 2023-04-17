package com.example.feature_main.data.mapper

import com.example.core.data.mapper.Mapper
import com.example.core.data.response.common.LaunchesResponse
import com.example.core.domain.entity.Launch

class LaunchesResponseMapper :
    Mapper<LaunchesResponse, List<Launch>>() {

    override fun mapFunction(from: LaunchesResponse): List<Launch> {
        return from.docs.map { launchResponse ->
            Launch(
                id = launchResponse.id,
                name = launchResponse.name,
                success = launchResponse.success,
                details = launchResponse.details.orEmpty(),
                links = launchResponse.links,
                cores = launchResponse.cores,
                fireDateUtc = launchResponse.fireDateUtc.orEmpty(),
            )
        }
    }
}
