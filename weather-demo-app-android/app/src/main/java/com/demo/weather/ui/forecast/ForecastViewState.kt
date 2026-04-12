package com.demo.weather.ui.forecast

import com.demo.weather.domain.model.ForecastDay

sealed interface ForecastViewState {
    data object Idle : ForecastViewState
    data object Loading : ForecastViewState
    data class Success(val days: List<ForecastDay>) : ForecastViewState
    data class Error(val message: String) : ForecastViewState
}