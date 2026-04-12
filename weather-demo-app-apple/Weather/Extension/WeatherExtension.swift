//
//  WeatherMapper.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

extension CurrentWeatherDTO {
    func toDomain() -> CurrentWeather {
        let firstWeather = weather.first

        return CurrentWeather(
            city: name,
            condition: firstWeather.toCondition(),
            description: firstWeather?.description ?? "",
            temperatureCelsius: main.temp,
            feelsLikeCelsius: main.feelsLike,
            humidityPercent: main.humidity,
            windSpeedKph: wind.speed * 3.6,
            iconCode: firstWeather?.icon ?? ""
        )
    }
}

extension ForecastDTO {
    func toDomainDaily() -> [ForecastDay] {
        let grouped = Dictionary(grouping: list) { item in
            item.dateText.components(separatedBy: " ").first ?? item.dateText
        }

        return grouped
            .sorted { $0.key < $1.key }
            .compactMap { date, items in
                let midday = items.first(where: { $0.dateText.contains("12:00:00") }) ?? items.first
                let firstWeather = midday?.weather.first

                guard let midday else { return nil }

                return ForecastDay(
                    date: date,
                    condition: firstWeather.toCondition(),
                    description: firstWeather?.description ?? "",
                    temperatureCelsius: midday.main.temp,
                    feelsLikeCelsius: midday.main.feelsLike,
                    iconCode: firstWeather?.icon ?? ""
                )
            }
            .prefix(5)
            .map { $0 }
    }
}

private extension Optional where Wrapped == WeatherInfoDTO {
    func toCondition() -> WeatherCondition {
        switch self?.main.lowercased() {
        case "clear":
            return .sunny
        case "clouds":
            return .cloudy
        case "rain", "drizzle":
            return .rain
        case "snow":
            return .snow
        case "thunderstorm":
            return .thunderstorm
        case "mist", "fog", "haze", "smoke":
            return .mist
        default:
            return .unknown
        }
    }
}
