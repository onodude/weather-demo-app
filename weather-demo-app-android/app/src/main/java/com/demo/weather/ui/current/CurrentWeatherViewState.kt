package com.demo.weather.ui.current

import com.demo.weather.domain.model.CurrentWeather

sealed interface CurrentWeatherViewState {
    data object Idle : CurrentWeatherViewState
    data object Loading : CurrentWeatherViewState
    data class Success(val weather: CurrentWeather) : CurrentWeatherViewState
    data class Error(val message: String) : CurrentWeatherViewState
}