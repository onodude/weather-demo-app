package com.demo.weather.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(
    val list: List<ForecastItemDto>
)