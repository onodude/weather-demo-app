package com.demo.weather

import com.demo.weather.data.FakeWeatherRepository
import com.demo.weather.ui.welcome.WelcomeViewModel
import org.junit.Assert.assertTrue
import org.junit.Test

class WelcomeViewModelTest {

    @Test
    fun `continueToApp updates welcome state`() {
        val repository = FakeWeatherRepository()
        val viewModel = WelcomeViewModel(repository)

        var didContinue = false

        viewModel.continueToApp {
            didContinue = true
        }

        assertTrue(viewModel.hasSeenWelcome.value)
        assertTrue(repository.hasSeenWelcome())
        assertTrue(didContinue)
    }
}