//
//  WeatherApp.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

@main
struct WeatherApp: App {
    private let repository: WeatherRepository

    private let welcomeViewModel: WelcomeViewModel
    private let currentWeatherViewModel: CurrentWeatherViewModel
    private let forecastViewModel: ForecastViewModel

    init() {
        let preferences = PreferencesRepository()
        let api = ApiClient()

        self.repository = WeatherRepository(api: api, preferences: preferences)

        self.welcomeViewModel = WelcomeViewModel(repository: repository)
        self.currentWeatherViewModel = CurrentWeatherViewModel(repository: repository)
        self.forecastViewModel = ForecastViewModel(repository: repository)
    }

    var body: some Scene {
        WindowGroup {
            WeatherAppRoot(
                welcomeViewModel: welcomeViewModel,
                currentWeatherViewModel: currentWeatherViewModel,
                forecastViewModel: forecastViewModel
            )
        }
    }
}
