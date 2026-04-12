//
//  UserDefaultsPreferencesStore.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

import Foundation

final class PreferencesRepository: PreferencesDataSource {
    private let defaults: UserDefaults

    private enum Keys {
        static let lastCity = "last_city"
        static let welcomeSeen = "welcome_seen"
    }

    init(defaults: UserDefaults = .standard) {
        self.defaults = defaults
    }

    func saveLastSearchedCity(_ city: String) {
        defaults.set(city, forKey: Keys.lastCity)
    }

    func getLastSearchedCity() -> String? {
        defaults.string(forKey: Keys.lastCity)
    }

    func setWelcomeSeen(_ seen: Bool) {
        defaults.set(seen, forKey: Keys.welcomeSeen)
    }

    func hasSeenWelcome() -> Bool {
        defaults.bool(forKey: Keys.welcomeSeen)
    }
}
