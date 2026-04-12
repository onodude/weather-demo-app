package com.demo.weather.ui.current

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel,
    onOpenForecast: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val city by viewModel.city.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLastCity()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Current Weather", fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->

        CurrentWeatherView(
            city = city,
            state = state,
            onCityChanged = viewModel::onCityChanged,
            onSearchClicked = { viewModel.search(city) },
            onRetryClicked = { viewModel.retry() },
            onForecastClicked = {
                (state as? CurrentWeatherViewState.Success)?.let {
                    onOpenForecast(it.weather.city)
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}