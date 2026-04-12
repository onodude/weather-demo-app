package com.demo.weather.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.demo.weather.data.PreferencesRepository
import com.demo.weather.data.WeatherRepository
import com.demo.weather.network.NetworkModule
import com.demo.weather.ui.current.CurrentWeatherViewModel
import com.demo.weather.ui.forecast.ForecastViewModel
import com.demo.weather.ui.welcome.WelcomeViewModel

class MainActivity : ComponentActivity() {

    private lateinit var repository: WeatherRepository
    private lateinit var welcomeViewModel: WelcomeViewModel
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences("weather_prefs", MODE_PRIVATE)
        val preferencesRepository = PreferencesRepository(preferences)
        val api = NetworkModule.weatherApi

        repository = WeatherRepository(
            api = api,
            preferences = preferencesRepository
        )

        welcomeViewModel = WelcomeViewModel(repository)
        currentWeatherViewModel = CurrentWeatherViewModel(repository)
        forecastViewModel = ForecastViewModel(repository)

        enableEdgeToEdge()

        setContent {
            WeatherAppRoot(
                welcomeViewModel = welcomeViewModel,
                currentWeatherViewModel = currentWeatherViewModel,
                forecastViewModel = forecastViewModel
            )
        }
    }
}