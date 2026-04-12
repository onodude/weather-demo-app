//
//  CurrentWeather.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

struct CurrentWeather: Equatable {
    let city: String
    let condition: WeatherCondition
    let description: String
    let temperatureCelsius: Double
    let feelsLikeCelsius: Double
    let humidityPercent: Int
    let windSpeedKph: Double
    let iconCode: String
}
