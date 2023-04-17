package com.example.feature_details.data.mapper

import com.example.core.data.mapper.Mapper
import com.example.core.domain.entity.Launch
import com.example.core.data.response.common.LaunchResponse

class LaunchResponseMapper :
    Mapper<LaunchResponse, Launch>() {

    override fun mapFunction(from: LaunchResponse): Launch {
        return Launch(
            id = from.id,
            name = from.name,
            success = from.success,
            details = from.details.orEmpty(),
            links = from.links,
            cores = from.cores,
            fireDateUtc = from.fireDateUtc.orEmpty(),
        )
    }
}
