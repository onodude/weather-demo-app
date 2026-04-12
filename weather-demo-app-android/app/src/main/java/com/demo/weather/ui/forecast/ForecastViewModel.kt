package com.demo.weather.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.weather.app.AppConstants
import com.demo.weather.data.WeatherDataSource
import com.demo.weather.domain.model.RepositoryError
import com.demo.weather.domain.model.toMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val repository: WeatherDataSource
) : ViewModel() {

    private val _state = MutableStateFlow<ForecastViewState>(ForecastViewState.Idle)
    val state: StateFlow<ForecastViewState> = _state.asStateFlow()

    private var lastLoadedCity: String? = null
    private var loadJob: Job? = null

    fun load(city: String, force: Boolean = false) {
        val targetCity = city.trim()

        if (!force &&
            lastLoadedCity == targetCity &&
            _state.value is ForecastViewState.Success
        ) {
            return
        }

        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _state.value = ForecastViewState.Loading

            try {
                val days = repository.getForecast(targetCity)
                lastLoadedCity = targetCity
                _state.value = ForecastViewState.Success(days)
            } catch (error: RepositoryError) {
                _state.value = ForecastViewState.Error(error.toMessage())
            } catch (_: Throwable) {
                _state.value = ForecastViewState.Error(AppConstants.GENERIC_ERROR_MESSAGE)
            }
        }
    }

    fun retry(city: String) {
        load(city = city, force = true)
    }
}