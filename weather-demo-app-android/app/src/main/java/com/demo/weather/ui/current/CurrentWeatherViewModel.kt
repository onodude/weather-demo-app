package com.demo.weather.ui.current

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

class CurrentWeatherViewModel(
    private val repository: WeatherDataSource
) : ViewModel() {

    private val _state = MutableStateFlow<CurrentWeatherViewState>(CurrentWeatherViewState.Idle)
    val state: StateFlow<CurrentWeatherViewState> = _state.asStateFlow()

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city.asStateFlow()

    private var hasLoadedInitialCity = false
    private var lastLoadedCity: String? = null
    private var searchJob: Job? = null

    fun onCityChanged(value: String) {
        _city.value = value
    }

    fun loadLastCity() {
        if (hasLoadedInitialCity) return
        hasLoadedInitialCity = true

        val lastCity = repository.getLastSearchedCity().orEmpty()
        if (lastCity.isEmpty()) return

        _city.value = lastCity
        search(city = lastCity)
    }

    fun search(city: String, force: Boolean = false) {
        val targetCity = city.trim()

        if (!force &&
            lastLoadedCity == targetCity &&
            _state.value is CurrentWeatherViewState.Success
        ) {
            return
        }

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            _state.value = CurrentWeatherViewState.Loading

            try {
                val result = repository.getCurrentWeather(city = targetCity)
                _city.value = result.city
                lastLoadedCity = result.city
                _state.value = CurrentWeatherViewState.Success(result)
            } catch (error: RepositoryError) {
                _state.value = CurrentWeatherViewState.Error(error.toMessage())
            } catch (_: Throwable) {
                _state.value = CurrentWeatherViewState.Error(AppConstants.GENERIC_ERROR_MESSAGE)
            }
        }
    }

    fun retry() {
        search(city = _city.value, force = true)
    }
}