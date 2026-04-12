//
//  WelcomeViewModel.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation
import Combine

@MainActor
final class WelcomeViewModel: ObservableObject {
    @Published private(set) var hasSeenWelcome: Bool

    private let repository: WeatherDataSource

    init(repository: WeatherDataSource) {
        self.repository = repository
        self.hasSeenWelcome = repository.hasSeenWelcome()
    }

    func continueToApp(onFinished: () -> Void) {
        repository.setWelcomeSeen(true)
        hasSeenWelcome = true
        onFinished()
    }
}
