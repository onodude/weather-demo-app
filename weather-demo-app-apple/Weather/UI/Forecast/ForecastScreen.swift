//
//  ForecastScreen.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import SwiftUI

struct ForecastScreen: View {
    @ObservedObject var viewModel: ForecastViewModel
    let city: String

    var body: some View {
        ForecastView(
            city: city,
            state: viewModel.state,
            onRetryClicked: {
                Task {
                    await viewModel.retry(city: city)
                }
            }
        )
        .navigationTitle("Forecast")
        .navigationBarTitleDisplayMode(.inline)
        .task(id: city) {
            await viewModel.load(city: city)
        }
    }
}
