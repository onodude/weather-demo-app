package com.demo.weather.ui.common

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demo.weather.app.AppConstants

@Composable
fun IconView(
    iconCode: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 72.dp
) {
    val url = "${AppConstants.WEATHER_ICON_BASE_URL}${iconCode}@2x.png"

    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}