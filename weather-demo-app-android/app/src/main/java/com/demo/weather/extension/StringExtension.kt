package com.demo.weather.extension

import com.demo.weather.domain.model.RepositoryError

fun String.requireCity(): String {
    if (isEmpty()) throw RepositoryError.EmptyCity()
    return this
}

fun String.normalizedCity(): String {
    return trim().requireCity()
}