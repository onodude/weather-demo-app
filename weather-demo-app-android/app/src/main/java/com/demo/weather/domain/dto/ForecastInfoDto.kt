package com.demo.weather.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfoDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)