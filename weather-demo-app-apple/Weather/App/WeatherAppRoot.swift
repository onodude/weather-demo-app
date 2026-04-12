//
//  WeatherAppRoot.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct WeatherAppRoot: View {
    @State private var path: [Route] = []

    @ObservedObject var welcomeViewModel: WelcomeViewModel
    @ObservedObject var currentWeatherViewModel: CurrentWeatherViewModel
    @ObservedObject var forecastViewModel: ForecastViewModel

    var body: some View {
        NavigationStack(path: $path) {
            Group {
                if welcomeViewModel.hasSeenWelcome {
                    CurrentWeatherScreen(
                        viewModel: currentWeatherViewModel,
                        onOpenForecast: { city in
                            path.append(.forecast(city: city))
                        }
                    )
                } else {
                    WelcomeScreen(
                        viewModel: welcomeViewModel,
                        onContinue: {
                            path = []
                        }
                    )
                }
            }
            .navigationDestination(for: Route.self) { route in
                switch route {
                case .forecast(let city):
                    ForecastScreen(
                        viewModel: forecastViewModel,
                        city: city
                    )

                default:
                    EmptyView()
                }
            }
        }
    }
}
