package com.demo.weather.data

import android.content.SharedPreferences
import androidx.core.content.edit


class PreferencesRepository(
    private val preferences: SharedPreferences
) : PreferencesDataSource {

    private object Keys {
        const val LAST_CITY = "last_city"
        const val WELCOME_SEEN = "welcome_seen"
    }

    override fun saveLastSearchedCity(city: String) {
        preferences.edit { putString(Keys.LAST_CITY, city) }
    }

    override fun getLastSearchedCity(): String? {
        return preferences.getString(Keys.LAST_CITY, null)
    }

    override fun setWelcomeSeen(seen: Boolean) {
        preferences.edit { putBoolean(Keys.WELCOME_SEEN, seen) }
    }

    override fun hasSeenWelcome(): Boolean {
        return preferences.getBoolean(Keys.WELCOME_SEEN, false)
    }
}