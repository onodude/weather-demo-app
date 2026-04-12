package com.demo.weather.extension

import com.demo.weather.domain.model.RepositoryError
import okio.IOException
import retrofit2.HttpException

fun Throwable.toRepositoryError(): RepositoryError {
    return when (this) {
        is RepositoryError -> this
        is IllegalArgumentException -> RepositoryError.EmptyCity()
        is IOException -> RepositoryError.Network()
        else -> RepositoryError.Unknown()
    }
}