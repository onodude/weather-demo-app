package com.demo.weather.ui.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.demo.weather.domain.model.ForecastDay
import com.demo.weather.ui.common.IconView
import java.util.Locale

@Composable
fun ForecastDayCardView(
    day: ForecastDay
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconView(
                iconCode = day.iconCode,
                contentDescription = day.description,
                size = 56.dp
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = day.date,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = day.description.capitalize(Locale.getDefault()),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Temp: ${"%.1f".format(Locale.US, day.temperatureCelsius)}°C")
                Text("Feels like: ${"%.1f".format(Locale.US, day.feelsLikeCelsius)}°C")
            }
        }
    }
}