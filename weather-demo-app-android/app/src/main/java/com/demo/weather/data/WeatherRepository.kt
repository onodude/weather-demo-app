package com.demo.weather.data

import com.demo.weather.domain.model.CurrentWeather
import com.demo.weather.domain.model.ForecastDay
import com.demo.weather.extension.toDomain
import com.demo.weather.extension.toDomainDaily
import com.demo.weather.extension.normalizedCity
import com.demo.weather.extension.toRepositoryError
import com.demo.weather.network.WeatherApi

class WeatherRepository(
    private val api: WeatherApi,
    private val preferences: PreferencesDataSource
) : WeatherDataSource {

    override suspend fun getCurrentWeather(city: String): CurrentWeather {
        return try {
            val normalizedCity = city.normalizedCity()

            val result = api.getCurrentWeather(
                city = normalizedCity
            ).toDomain()

            preferences.saveLastSearchedCity(result.city)
            result

        } catch (t: Throwable) {
            throw t.toRepositoryError()
        }
    }

    override suspend fun getForecast(city: String): List<ForecastDay> {
        return try {
            val normalizedCity = city.normalizedCity()

            api.getForecast(
                city = normalizedCity
            ).toDomainDaily()

        } catch (t: Throwable) {
            throw t.toRepositoryError()
        }
    }

    override fun saveLastSearchedCity(city: String) {
        preferences.saveLastSearchedCity(city.trim())
    }

    override fun getLastSearchedCity(): String? {
        return preferences.getLastSearchedCity()
    }

    override fun setWelcomeSeen(seen: Boolean) {
        preferences.setWelcomeSeen(seen)
    }

    override fun hasSeenWelcome(): Boolean {
        return preferences.hasSeenWelcome()
    }
}