package com.example.feature_main.domain

import com.example.core.domain.mapper.Either
import com.example.feature_main.domain.entity.Launch

interface MainRepository  {
    suspend fun getLaunches(): Either<List<Launch>>
}
