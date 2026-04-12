package com.demo.weather.domain.model

data class ForecastDay(
    val date: String,
    val condition: WeatherCondition,
    val description: String,
    val temperatureCelsius: Double,
    val feelsLikeCelsius: Double,
    val iconCode: String
)