package com.demo.weather.ui.current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.demo.weather.domain.model.CurrentWeather
import com.demo.weather.ui.common.IconView
import java.util.Locale

@Composable
fun CurrentWeatherSuccessView(
    weather: CurrentWeather,
    onForecastClicked: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconView(
                    iconCode = weather.iconCode,
                    contentDescription = weather.description,
                    size = 72.dp
                )

                Column {
                    Text(
                        text = weather.city,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = weather.description.capitalize(Locale.getDefault()),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("${"%.1f".format(Locale.US, weather.temperatureCelsius)}°C")
            Text("Feels like ${"%.1f".format(Locale.US, weather.feelsLikeCelsius)}°C")

            Spacer(modifier = Modifier.height(16.dp))

            Text("Humidity: ${weather.humidityPercent}%")
            Text("Wind speed: ${"%.1f".format(Locale.US, weather.windSpeedKph)} km/h")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onForecastClicked
            ) {
                Text("See forecast")
            }
        }
    }
}