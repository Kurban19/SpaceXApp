package com.example.feature_main.data

import com.example.core.data.toEither
import com.example.core.domain.mapper.Either
import com.example.feature_main.data.api.Filter
import com.example.feature_main.data.api.MainService
import com.example.feature_main.data.api.Options
import com.example.feature_main.data.mapper.LaunchesResponseMapper
import com.example.feature_main.domain.MainRepository
import com.example.core.domain.entity.Launch
import javax.inject.Inject

//val defaultFilter = Filter(
//    query = Query(dateUtc = mapOf("gt" to "2021-01-24T22:30:00.000Z")),
//    options = Options(limit = 10, offset = 2, sort = mapOf("date_utc" to "asc"))
//)

val defaultFilter = Filter(
//    query = Query(),
    options = Options(limit = 10, offset = 10, page = 0)
)

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {
    override suspend fun getLaunches(): Either<List<Launch>> =
        mainService.getLaunches(defaultFilter).toEither(LaunchesResponseMapper())
}
