package com.demo.weather.data

import com.demo.weather.domain.model.CurrentWeather
import com.demo.weather.domain.model.ForecastDay
import com.demo.weather.domain.model.WeatherCondition

class FakeWeatherRepository : WeatherDataSource {

    var hasSeenWelcomeValue: Boolean = false
    var lastSearchedCityValue: String? = null

    override suspend fun getCurrentWeather(city: String): CurrentWeather {
        return CurrentWeather(
            city = city,
            condition = WeatherCondition.CLOUDY,
            description = "broken clouds",
            temperatureCelsius = 20.0,
            feelsLikeCelsius = 19.0,
            humidityPercent = 60,
            windSpeedKph = 18.0,
            iconCode = "04d"
        )
    }

    override suspend fun getForecast(city: String): List<ForecastDay> {
        return listOf(
            ForecastDay(
                date = "2026-04-12",
                condition = WeatherCondition.RAIN,
                description = "light rain",
                temperatureCelsius = 18.0,
                feelsLikeCelsius = 17.0,
                iconCode = "10d"
            )
        )
    }

    override fun saveLastSearchedCity(city: String) {
        lastSearchedCityValue = city
    }

    override fun getLastSearchedCity(): String? {
        return lastSearchedCityValue
    }

    override fun setWelcomeSeen(seen: Boolean) {
        hasSeenWelcomeValue = seen
    }

    override fun hasSeenWelcome(): Boolean {
        return hasSeenWelcomeValue
    }
}