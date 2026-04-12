package com.demo.weather.network

import com.demo.weather.app.AppConstants
import com.demo.weather.domain.dto.CurrentWeatherDto
import com.demo.weather.domain.dto.ForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = AppConstants.API_KEY,
        @Query("units") units: String = AppConstants.DEFAULT_UNITS
    ): CurrentWeatherDto

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String = AppConstants.API_KEY,
        @Query("units") units: String = AppConstants.DEFAULT_UNITS
    ): ForecastDto
}