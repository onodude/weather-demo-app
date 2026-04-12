package com.demo.weather.ui.forecast

import ForecastView
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(
    city: String,
    viewModel: ForecastViewModel,
    onBackClicked: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(city) {
        viewModel.load(city)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Forecast", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ForecastView(
            city = city,
            state = state,
            onRetryClicked = { viewModel.retry(city) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}