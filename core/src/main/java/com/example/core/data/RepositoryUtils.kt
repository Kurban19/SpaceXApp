package com.example.core.data

import com.example.core.data.mapper.Mapper
import com.example.core.data.response.NetworkResponse
import com.example.core.domain.mapper.Either
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}

fun <T : NetworkResponse, R> Response<T>.toEither(
    mapper: Mapper<T, R>,
): Either<R> = bodyOrThrow()
    .let {
        try {
            mapper.map(it)
        } catch (e: Exception) {
            Either.Failure(e, e.message)
        }
    }
