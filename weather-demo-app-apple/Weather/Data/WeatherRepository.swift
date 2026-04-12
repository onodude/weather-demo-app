//
//  WeatherRepository.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

final class WeatherRepository: WeatherDataSource {
    private let api: WeatherApi
    private let preferences: PreferencesDataSource

    init(
        api: WeatherApi,
        preferences: PreferencesDataSource
    ) {
        self.api = api
        self.preferences = preferences
    }

    func getCurrentWeather(city: String) async throws -> CurrentWeather {
        do {
            let normalizedCity = try city.normalizedCity()

            let result = try await api.getCurrentWeather(
                city: normalizedCity
            ).toDomain()

            preferences.saveLastSearchedCity(result.city)
            return result

        } catch {
            throw error.toRepositoryError()
        }
    }

    func getForecast(city: String) async throws -> [ForecastDay] {
        let normalizedCity = try city.normalizedCity()

        return try await api.getForecast(
            city: normalizedCity
        ).toDomainDaily()
    }

    func saveLastSearchedCity(_ city: String) {
        preferences.saveLastSearchedCity(
            city.trimmingCharacters(in: .whitespacesAndNewlines)
        )
    }

    func getLastSearchedCity() -> String? {
        return preferences.getLastSearchedCity()
    }

    func setWelcomeSeen(_ seen: Bool) {
        preferences.setWelcomeSeen(seen)
    }

    func hasSeenWelcome() -> Bool {
        return preferences.hasSeenWelcome()
    }
}
