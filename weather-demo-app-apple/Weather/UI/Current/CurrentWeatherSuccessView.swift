//
//  CurrentWeatherSuccessView.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct CurrentWeatherSuccessView: View {
    let weather: CurrentWeather
    let onForecastClicked: () -> Void

    var body: some View {
        VStack(alignment: .leading) {
            HStack(spacing: 12) {
                IconView(iconCode: weather.iconCode, size: 72)

                VStack(alignment: .leading) {
                    Text(weather.city)
                        .font(.title3)

                    Text(weather.description.capitalized)
                        .font(.body)
                }
            }
            
            Spacer().frame(height: 16)

            Text("\(weather.temperatureCelsius, specifier: "%.1f")°C")
            Text("Feels like \(weather.feelsLikeCelsius, specifier: "%.1f")°C")
            
            Spacer().frame(height: 16)

            Text("Humidity: \(weather.humidityPercent)%")
            Text("Wind speed: \(weather.windSpeedKph, specifier: "%.1f") km/h")
            
            Spacer().frame(height: 20)

            Button("See forecast", action: onForecastClicked)
                .buttonStyle(.borderedProminent)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(16)
        .background(.thinMaterial)
        .clipShape(RoundedRectangle(cornerRadius: 16))
    }
}
