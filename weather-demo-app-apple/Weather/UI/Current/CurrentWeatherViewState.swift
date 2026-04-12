//
//  CurrentWeatherViewState.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

enum CurrentWeatherViewState: Equatable {
    case idle
    case loading
    case success(CurrentWeather)
    case error(String)
}
