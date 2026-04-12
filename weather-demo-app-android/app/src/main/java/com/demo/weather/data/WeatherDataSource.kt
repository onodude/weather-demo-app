package com.demo.weather.data

import com.demo.weather.domain.model.CurrentWeather
import com.demo.weather.domain.model.ForecastDay

interface WeatherDataSource {
    suspend fun getCurrentWeather(city: String): CurrentWeather
    suspend fun getForecast(city: String): List<ForecastDay>

    fun saveLastSearchedCity(city: String)
    fun getLastSearchedCity(): String?

    fun setWelcomeSeen(seen: Boolean)
    fun hasSeenWelcome(): Boolean
}