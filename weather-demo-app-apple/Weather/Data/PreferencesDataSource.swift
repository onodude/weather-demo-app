//
//  PreferencesDataSource.swift
//  Weather
//
//  Created by Onur on 4/11/26.
//

protocol PreferencesDataSource {
    func saveLastSearchedCity(_ city: String)
    func getLastSearchedCity() -> String?
    func setWelcomeSeen(_ seen: Bool)
    func hasSeenWelcome() -> Bool
}
