//
//  CurrentWeatherView.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct CurrentWeatherView: View {
    let city: String
    let state: CurrentWeatherViewState

    let onCityChanged: (String) -> Void
    let onSearchClicked: () -> Void
    let onRetryClicked: () -> Void
    let onForecastClicked: () -> Void

    var body: some View {
        VStack(alignment: .leading) {
            Text("Search by city to view current conditions and forecast.")
                .font(.subheadline)
            
            Spacer().frame(height: 16)

            TextField(
                "City",
                text: Binding(
                    get: { city },
                    set: { onCityChanged($0) }
                )
            )
            .textFieldStyle(.roundedBorder)
            
            Spacer().frame(height: 12)

            Button("Search", action: onSearchClicked)
                .buttonStyle(.borderedProminent)
            
            Spacer().frame(height: 24)

            Group {
                switch state {
                case .idle:
                    IdleView(message: "Enter a city to get started.")

                case .loading:
                    LoadingView()

                case .error(let message):
                    ErrorView(
                        message: message,
                        onRetryClicked: onRetryClicked
                    )

                case .success(let weather):
                    CurrentWeatherSuccessView(
                        weather: weather,
                        onForecastClicked: onForecastClicked
                    )
                }
            }
            
            Spacer()
        }
        .padding(16)
    }
}
