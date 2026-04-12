//
//  ForecastViewModel.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation
import Combine

@MainActor
final class ForecastViewModel: ObservableObject {
    @Published private(set) var state: ForecastViewState = .idle

    private let repository: WeatherDataSource
    private var lastLoadedCity: String?
    private var loadTask: Task<Void, Never>?

    init(repository: WeatherDataSource) {
        self.repository = repository
    }

    func load(city: String, force: Bool = false) async {
        let targetCity = city.trimmingCharacters(in: .whitespacesAndNewlines)

        if !force,
           lastLoadedCity == targetCity,
           case .success = state {
            return
        }

        loadTask?.cancel()

        loadTask = Task {
            state = .loading

            do {
                let days = try await repository.getForecast(city: targetCity)
                self.lastLoadedCity = targetCity
                self.state = .success(days)
            } catch let error as RepositoryError {
                self.state = .error(error.message)
            } catch {
                self.state = .error(AppConstants.genericErrorMessage)
            }
        }

        await loadTask?.value
    }

    func retry(city: String) async {
        await load(city: city, force: true)
    }
}
