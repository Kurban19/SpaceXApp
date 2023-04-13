package com.example.feature_main.data.mapper

import com.example.core.data.mapper.Mapper
import com.example.feature_main.data.response.LaunchesResponse
import com.example.feature_main.domain.entity.Launch

class LauncherResponseMapper :
    Mapper<LaunchesResponse, List<Launch>>() {

    override fun mapFunction(from: LaunchesResponse): List<Launch> {
        return from.docs.map { launchResponse ->
            Launch(
                id = launchResponse.id,
                name = launchResponse.name,
                success = launchResponse.success,
                links = launchResponse.links,
                cores = launchResponse.cores,
                fireDateUtc = launchResponse.fireDateUtc.orEmpty(),
            )
        }
    }
}
