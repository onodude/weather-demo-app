package com.demo.weather

import com.demo.weather.data.FakeWeatherRepository
import com.demo.weather.ui.current.CurrentWeatherViewModel
import com.demo.weather.ui.current.CurrentWeatherViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `search updates state to success`() = runTest {
        val repository = FakeWeatherRepository()
        val viewModel = CurrentWeatherViewModel(repository)

        viewModel.search(city = "London")
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CurrentWeatherViewState.Success)

        val success = state as CurrentWeatherViewState.Success
        assertEquals("London", success.weather.city)
    }
}