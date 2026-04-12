package com.demo.weather.domain.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastItemDto(
    @SerialName("dt_txt")
    val dateText: String,
    val weather: List<WeatherInfoDto>,
    val main: MainInfoDto
)