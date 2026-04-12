//
//  CurrentWeatherViewModel.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation
import Combine

@MainActor
final class CurrentWeatherViewModel: ObservableObject {
    @Published private(set) var state: CurrentWeatherViewState = .idle
    @Published var city: String = ""

    private let repository: WeatherDataSource
    private var hasLoadedInitialCity = false
    private var lastLoadedCity: String?
    private var searchTask: Task<Void, Never>?

    init(repository: WeatherDataSource) {
        self.repository = repository
    }

    func onCityChanged(_ value: String) {
        city = value
    }

    func loadLastCity() async {
        guard !hasLoadedInitialCity else { return }
        hasLoadedInitialCity = true

        let lastCity = repository.getLastSearchedCity().orEmpty
        guard !lastCity.isEmpty else { return }

        city = lastCity
        await search(city: lastCity)
    }

    func search(city: String, force: Bool = false) async {
        let targetCity = city.trimmingCharacters(in: .whitespacesAndNewlines)

        if !force,
           lastLoadedCity == targetCity,
           case .success = state {
            return
        }

        searchTask?.cancel()

        searchTask = Task {
            state = .loading

            do {
                let result = try await repository.getCurrentWeather(city: targetCity)
                self.city = result.city
                self.lastLoadedCity = result.city
                self.state = .success(result)
            } catch let error as RepositoryError {
                self.state = .error(error.message)
            } catch {
                self.state = .error(AppConstants.genericErrorMessage)
            }
        }

        await searchTask?.value
    }

    func retry() async {
        await search(city: city, force: true)
    }
}
