package com.example.core.data.mapper

import com.example.core.data.response.NetworkResponse
import com.example.core.domain.mapper.Either

abstract class Mapper<From : NetworkResponse, To> {
    fun map(from: From): Either<To> {
        return Either.Success(mapFunction(from))
    }

    protected abstract fun mapFunction(from: From): To
}
