//
//  ApiClient.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

final class ApiClient: WeatherApi {
    private let session: URLSession
    private let decoder: JSONDecoder
    private let apiKey: String

    init(
        session: URLSession = .shared,
        decoder: JSONDecoder = JSONDecoder(),
        apiKey: String = AppConstants.apiKey
    ) {
        self.session = session
        self.decoder = decoder
        self.apiKey = apiKey
    }

    func getCurrentWeather(city: String) async throws -> CurrentWeatherDTO {
        let url = try makeURL(path: "data/2.5/weather", city: city)
        let (data, response) = try await session.data(from: url)
        try validate(response: response)
        return try decoder.decode(CurrentWeatherDTO.self, from: data)
    }

    func getForecast(city: String) async throws -> ForecastDTO {
        let url = try makeURL(path: "data/2.5/forecast", city: city)
        let (data, response) = try await session.data(from: url)
        try validate(response: response)
        return try decoder.decode(ForecastDTO.self, from: data)
    }

    private func makeURL(path: String, city: String) throws -> URL {
        var components = URLComponents(string: AppConstants.baseURL + path)

        components?.queryItems = [
            URLQueryItem(name: "q", value: city),
            URLQueryItem(name: "appid", value: AppConstants.apiKey),
            URLQueryItem(name: "units", value: AppConstants.units)
        ]

        guard let url = components?.url else {
            throw NetworkError.invalidURL
        }

        return url
    }

    private func validate(response: URLResponse) throws {
        guard let http = response as? HTTPURLResponse else {
            throw NetworkError.invalidResponse
        }

        guard 200..<300 ~= http.statusCode else {
            throw NetworkError.httpError(http.statusCode)
        }
    }
}
