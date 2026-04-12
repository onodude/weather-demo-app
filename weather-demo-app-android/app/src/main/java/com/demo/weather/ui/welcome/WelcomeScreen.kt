package com.demo.weather.ui.welcome

import androidx.compose.runtime.Composable

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel,
    onContinue: () -> Unit
) {
    WelcomeView(
        onContinueClicked = {
            viewModel.continueToApp(onFinished = onContinue)
        }
    )
}