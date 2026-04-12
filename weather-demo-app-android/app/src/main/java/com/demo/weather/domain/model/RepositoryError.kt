package com.demo.weather.domain.model

sealed class RepositoryError : Exception() {
    class EmptyCity : RepositoryError()
    class Network : RepositoryError()
    class Unknown : RepositoryError()
}

fun RepositoryError.toMessage(): String {
    return when (this) {
        is RepositoryError.EmptyCity -> "Please enter a city"
        is RepositoryError.Network -> "Network error. Please try again."
        is RepositoryError.Unknown -> "Something went wrong"
    }
}