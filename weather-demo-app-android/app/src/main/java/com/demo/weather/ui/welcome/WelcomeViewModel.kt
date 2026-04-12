package com.demo.weather.ui.welcome

import androidx.lifecycle.ViewModel
import com.demo.weather.data.WeatherDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WelcomeViewModel(
    private val repository: WeatherDataSource
) : ViewModel() {

    private val _hasSeenWelcome = MutableStateFlow(repository.hasSeenWelcome())
    val hasSeenWelcome: StateFlow<Boolean> = _hasSeenWelcome.asStateFlow()

    fun continueToApp(onFinished: () -> Unit) {
        repository.setWelcomeSeen(true)
        _hasSeenWelcome.value = true
        onFinished()
    }
}