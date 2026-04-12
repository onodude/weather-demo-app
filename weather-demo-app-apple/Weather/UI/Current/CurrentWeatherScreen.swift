//
//  CurrentWeatherScreen.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct CurrentWeatherScreen: View {
    @ObservedObject var viewModel: CurrentWeatherViewModel
    let onOpenForecast: (String) -> Void

    var body: some View {
        CurrentWeatherView(
            city: viewModel.city,
            state: viewModel.state,
            onCityChanged: viewModel.onCityChanged,
            onSearchClicked: {
                Task {
                    await viewModel.search(city: viewModel.city)
                }
            },
            onRetryClicked: {
                Task {
                    await viewModel.retry()
                }
            },
            onForecastClicked: {
                let forecastCity: String
                switch viewModel.state {
                case .success(let weather):
                    forecastCity = weather.city
                default:
                    forecastCity = viewModel.city
                }
                onOpenForecast(forecastCity)
            }
        )
        .navigationTitle("Current Weather")
        .navigationBarTitleDisplayMode(.inline)
        .task {
            await viewModel.loadLastCity()
        }
    }
}
