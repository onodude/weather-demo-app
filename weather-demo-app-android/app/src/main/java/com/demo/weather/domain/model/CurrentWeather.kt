package com.demo.weather.domain.model

data class CurrentWeather(
    val city: String,
    val condition: WeatherCondition,
    val description: String,
    val temperatureCelsius: Double,
    val feelsLikeCelsius: Double,
    val humidityPercent: Int,
    val windSpeedKph: Double,
    val iconCode: String
)