package com.demo.weather.ui.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.demo.weather.domain.model.ForecastDay

@Composable
fun ForecastSuccessView(
    days: List<ForecastDay>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(days) { day ->
            ForecastDayCardView(day = day)
        }
    }
}