package com.demo.weather.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.demo.weather.ui.current.CurrentWeatherScreen
import com.demo.weather.ui.current.CurrentWeatherViewModel
import com.demo.weather.ui.forecast.ForecastScreen
import com.demo.weather.ui.forecast.ForecastViewModel
import com.demo.weather.ui.welcome.WelcomeScreen
import com.demo.weather.ui.welcome.WelcomeViewModel

@Composable
fun WeatherAppRoot(
    welcomeViewModel: WelcomeViewModel,
    currentWeatherViewModel: CurrentWeatherViewModel,
    forecastViewModel: ForecastViewModel
) {
    val hasSeenWelcome by welcomeViewModel.hasSeenWelcome.collectAsState()
    val startDestination = if (hasSeenWelcome) Route.Current else Route.Welcome

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Route.Welcome> {
            WelcomeScreen(
                viewModel = welcomeViewModel,
                onContinue = {
                    navController.navigate(Route.Current) {
                        popUpTo(Route.Welcome) { inclusive = true }
                    }
                }
            )
        }

        composable<Route.Current> {
            CurrentWeatherScreen(
                viewModel = currentWeatherViewModel,
                onOpenForecast = { city ->
                    navController.navigate(Route.Forecast(city = city))
                }
            )
        }

        composable<Route.Forecast> { entry ->
            val args = entry.toRoute<Route.Forecast>()
            val city = args.city

            ForecastScreen(
                city = city,
                viewModel = forecastViewModel,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}