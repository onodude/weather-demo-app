package com.demo.weather.data

interface PreferencesDataSource {
    fun saveLastSearchedCity(city: String)
    fun getLastSearchedCity(): String?

    fun setWelcomeSeen(seen: Boolean)
    fun hasSeenWelcome(): Boolean
}