package com.demo.weather.extension

import com.demo.weather.domain.dto.CurrentWeatherDto
import com.demo.weather.domain.dto.ForecastDto
import com.demo.weather.domain.dto.WeatherInfoDto
import com.demo.weather.domain.model.CurrentWeather
import com.demo.weather.domain.model.ForecastDay
import com.demo.weather.domain.model.WeatherCondition

fun CurrentWeatherDto.toDomain(): CurrentWeather {
    val firstWeather = weather.firstOrNull()

    return CurrentWeather(
        city = name,
        condition = firstWeather.toCondition(),
        description = firstWeather?.description.orEmpty(),
        temperatureCelsius = main.temp,
        feelsLikeCelsius = main.feelsLike,
        humidityPercent = main.humidity,
        windSpeedKph = wind.speed * 3.6,
        iconCode = firstWeather?.icon.orEmpty()
    )
}

fun ForecastDto.toDomainDaily(): List<ForecastDay> {
    return list
        .groupBy { it.dateText.substringBefore(" ") }
        .mapNotNull { (date, items) ->
            val midday = items.firstOrNull { it.dateText.contains("12:00:00") } ?: items.firstOrNull()
            val firstWeather = midday?.weather?.firstOrNull()

            midday?.let {
                ForecastDay(
                    date = date,
                    condition = firstWeather.toCondition(),
                    description = firstWeather?.description.orEmpty(),
                    temperatureCelsius = it.main.temp,
                    feelsLikeCelsius = it.main.feelsLike,
                    iconCode = firstWeather?.icon.orEmpty()
                )
            }
        }
        .take(5)
}

private fun WeatherInfoDto?.toCondition(): WeatherCondition {
    return when (this?.main?.lowercase()) {
        "clear" -> WeatherCondition.SUNNY
        "clouds" -> WeatherCondition.CLOUDY
        "rain", "drizzle" -> WeatherCondition.RAIN
        "snow" -> WeatherCondition.SNOW
        "thunderstorm" -> WeatherCondition.THUNDERSTORM
        "mist", "fog", "haze", "smoke" -> WeatherCondition.MIST
        else -> WeatherCondition.UNKNOWN
    }
}