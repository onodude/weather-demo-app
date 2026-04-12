//
//  ForecastView.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct ForecastView: View {
    let city: String
    let state: ForecastViewState
    let onRetryClicked: () -> Void

    var body: some View {
        VStack(alignment: .leading) {
            Text(city)
                .font(.title3)

            Text("5-day outlook")
                .font(.subheadline)

            Spacer().frame(height: 12)

            Group {
                switch state {
                case .idle:
                    IdleView(message: "Loading forecast...")

                case .loading:
                    LoadingView()

                case .error(let message):
                    ErrorView(
                        message: message,
                        onRetryClicked: onRetryClicked
                    )

                case .success(let days):
                    ForecastSuccessView(days: days)
                }
            }
            
            Spacer()
        }
        .padding(16)
    }
}
