package com.demo.weather

import com.demo.weather.data.FakeWeatherRepository
import com.demo.weather.ui.forecast.ForecastViewModel
import com.demo.weather.ui.forecast.ForecastViewState
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
class ForecastViewModelTest {

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
    fun `load updates state to success`() = runTest {
        val repository = FakeWeatherRepository()
        val viewModel = ForecastViewModel(repository)

        viewModel.load(city = "London")
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ForecastViewState.Success)

        val success = state as ForecastViewState.Success
        assertEquals(1, success.days.size)
        assertEquals("2026-04-12", success.days.first().date)
    }
}