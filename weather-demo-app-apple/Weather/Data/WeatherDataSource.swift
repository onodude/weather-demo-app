//
//  WeatherDataSource.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

protocol WeatherDataSource {
    func getCurrentWeather(city: String) async throws -> CurrentWeather
    func getForecast(city: String) async throws -> [ForecastDay]

    func saveLastSearchedCity(_ city: String)
    func getLastSearchedCity() -> String?

    func setWelcomeSeen(_ seen: Bool)
    func hasSeenWelcome() -> Bool
}
