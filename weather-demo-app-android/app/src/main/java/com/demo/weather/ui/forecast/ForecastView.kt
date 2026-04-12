import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demo.weather.ui.common.ErrorView
import com.demo.weather.ui.common.IdleView
import com.demo.weather.ui.common.LoadingView
import com.demo.weather.ui.forecast.ForecastSuccessView
import com.demo.weather.ui.forecast.ForecastViewState

@Composable
fun ForecastView(
    city: String,
    state: ForecastViewState,
    onRetryClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = city,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "5-day outlook",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        when (state) {
            ForecastViewState.Idle -> {
                IdleView(message = "Loading forecast...")
            }

            ForecastViewState.Loading -> {
                LoadingView()
            }

            is ForecastViewState.Error -> {
                ErrorView(
                    message = state.message,
                    onRetryClicked = onRetryClicked
                )
            }

            is ForecastViewState.Success -> {
                ForecastSuccessView(days = state.days)
            }
        }
    }
}