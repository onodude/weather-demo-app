package com.demo.weather.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainInfoDto(
    val temp: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    val humidity: Int
)