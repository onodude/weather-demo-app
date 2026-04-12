//
//  ForecastDayCardView.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import SwiftUI

struct ForecastDayCardView: View {
    let day: ForecastDay

    var body: some View {
        HStack(alignment: .center, spacing: 12) {
            IconView(iconCode: day.iconCode, size: 56)

            VStack(alignment: .leading) {
                Text(day.date)
                    .font(.headline)

                Text(day.description.capitalized)
                    .font(.subheadline)
                    .foregroundStyle(.secondary)

                Spacer().frame(height: 8)

                Text("Temp: \(day.temperatureCelsius, specifier: "%.1f")°C")
                Text("Feels like: \(day.feelsLikeCelsius, specifier: "%.1f")°C")
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 14)
                .fill(Color(.systemGray6))
        )
    }
}
