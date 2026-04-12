//
//  ForecastViewModelTests.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import Testing
@testable import Weather

@MainActor
struct ForecastViewModelTests {

    @Test
    func load_updatesStateToSuccess() async {
        let repository = FakeWeatherRepository()
        let viewModel = ForecastViewModel(repository: repository)

        await viewModel.load(city: "London")

        switch viewModel.state {
        case .success(let days):
            #expect(days.count == 1)
            #expect(days.first?.date == "2026-04-12")
        default:
            Issue.record("Expected success state")
        }
    }
}
