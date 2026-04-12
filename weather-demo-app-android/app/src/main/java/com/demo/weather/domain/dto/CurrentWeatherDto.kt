package com.demo.weather.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    val name: String,
    val weather: List<WeatherInfoDto>,
    val main: MainInfoDto,
    val wind: WindDto
)