package com.demo.weather.app

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Welcome: Route()
    @Serializable
    data object Current: Route()
    @Serializable
    data class Forecast(val city: String): Route()
}