//
//  WelcomeViewModelTests.swift
//  Weather
//
//  Created by Onur on 4/12/26.
//

import Testing
@testable import Weather

@MainActor
struct WelcomeViewModelTests {

    @Test
    func continueToApp_updatesWelcomeState() {
        let repository = FakeWeatherRepository()
        let viewModel = WelcomeViewModel(repository: repository)

        var didContinue = false

        viewModel.continueToApp {
            didContinue = true
        }

        #expect(viewModel.hasSeenWelcome == true)
        #expect(repository.hasSeenWelcome() == true)
        #expect(didContinue == true)
    }
}
