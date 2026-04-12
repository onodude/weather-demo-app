//
//  ForecastDay.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

struct ForecastDay: Equatable, Identifiable {
    let id = UUID()
    let date: String
    let condition: WeatherCondition
    let description: String
    let temperatureCelsius: Double
    let feelsLikeCelsius: Double
    let iconCode: String
}
