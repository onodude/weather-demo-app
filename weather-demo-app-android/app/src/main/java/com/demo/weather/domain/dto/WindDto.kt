package com.demo.weather.domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class WindDto(
    val speed: Double
)