//
//  FakeWeatherRepository.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import Foundation

final class FakeWeatherRepository: WeatherDataSource {
    var hasSeenWelcomeValue: Bool = false
    var lastSearchedCityValue: String?

    func getCurrentWeather(city: String) async throws -> CurrentWeather {
        CurrentWeather(
            city: city,
            condition: .cloudy,
            description: "broken clouds",
            temperatureCelsius: 20.0,
            feelsLikeCelsius: 19.0,
            humidityPercent: 60,
            windSpeedKph: 18.0,
            iconCode: "04d"
        )
    }

    func getForecast(city: String) async throws -> [ForecastDay] {
        [
            ForecastDay(
                date: "2026-04-12",
                condition: .rain,
                description: "light rain",
                temperatureCelsius: 18.0,
                feelsLikeCelsius: 17.0,
                iconCode: "10d"
            )
        ]
    }

    func saveLastSearchedCity(_ city: String) {
        lastSearchedCityValue = city
    }

    func getLastSearchedCity() -> String? {
        lastSearchedCityValue
    }

    func setWelcomeSeen(_ seen: Bool) {
        hasSeenWelcomeValue = seen
    }

    func hasSeenWelcome() -> Bool {
        hasSeenWelcomeValue
    }
}
