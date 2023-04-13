package com.example.core.domain.mapper

import java.io.Serializable

/**
 * Представляет значение двух возможных результатов
 */
sealed class Either<out T> : Serializable {
    data class Success<T>(
        val value: T,
    ) : Either<T>()

    data class Failure<T>(
        val e: Exception,
        val message: String? = null,
    ) : Either<T>()
}
