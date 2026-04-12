//
//  WeatherApi.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

protocol WeatherApi {
    func getCurrentWeather(city: String) async throws -> CurrentWeatherDTO
    func getForecast(city: String) async throws -> ForecastDTO
}
