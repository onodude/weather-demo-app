package com.demo.weather.ui.current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demo.weather.ui.common.ErrorView
import com.demo.weather.ui.common.IdleView
import com.demo.weather.ui.common.LoadingView

@Composable
fun CurrentWeatherView(
    city: String,
    state: CurrentWeatherViewState,
    onCityChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onRetryClicked: () -> Unit,
    onForecastClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Search by city to view current conditions and forecast.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = city,
            onValueChange = onCityChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("City") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSearchClicked
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (state) {
            CurrentWeatherViewState.Idle -> {
                IdleView(message = "Enter a city to get started.")
            }

            CurrentWeatherViewState.Loading -> {
                LoadingView()
            }

            is CurrentWeatherViewState.Error -> {
                ErrorView(
                    message = state.message,
                    onRetryClicked = onRetryClicked
                )
            }

            is CurrentWeatherViewState.Success -> {
                CurrentWeatherSuccessView(
                    weather = state.weather,
                    onForecastClicked = onForecastClicked
                )
            }
        }
    }
}