//
//  CurrentWeatherViewModelTests.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import Testing
@testable import Weather

@MainActor
struct CurrentWeatherViewModelTests {

    @Test
    func search_updatesStateToSuccess() async {
        let repository = FakeWeatherRepository()
        let viewModel = CurrentWeatherViewModel(repository: repository)

        await viewModel.search(city: "London")

        switch viewModel.state {
        case .success(let weather):
            #expect(weather.city == "London")
        default:
            Issue.record("Expected success state")
        }
    }
}
