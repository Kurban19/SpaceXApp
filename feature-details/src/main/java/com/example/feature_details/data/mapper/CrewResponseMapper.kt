package com.example.feature_details.data.mapper

import com.example.core.data.mapper.Mapper
import com.example.feature_details.data.response.CrewsResponse
import com.example.feature_details.domain.entity.Crew

class CrewResponseMapper :
    Mapper<CrewsResponse, List<Crew>>() {

    override fun mapFunction(from: CrewsResponse): List<Crew> {
        return from.map {
            Crew(
                id = it.id,
                name = it.name,
                agency = it.agency,
                image = it.image,
                status = it.status
            )
        }
    }
}
