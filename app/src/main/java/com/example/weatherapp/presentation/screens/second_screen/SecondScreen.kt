package com.example.weatherapp.presentation.screens.second_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.domain.models.WeatherCurrentData

@Composable
fun SecondScreen(secondScreenViewModel: SecondScreenViewModel, cityName: String) {

//    val secondScreenViewModel: SecondScreenViewModel = viewModel(
//        factory = SecondScreenViewModelFactory(LocalContext.current)
//    )

    val viewState = secondScreenViewModel.weatherCurrentUiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        secondScreenViewModel.getEvent(
            intent = SecondScreenIntent.CurrentWeatherFetch(cityName)
        )
    })

    SideEffect {
        Log.e("side_current_weather", "$viewState")
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(val state = viewState.value) {
                is WeatherCurrentUiState.Empty -> Text(
                    text = "Empty state occurred"
                )
                is WeatherCurrentUiState.Loading ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                is WeatherCurrentUiState.Success -> WeatherLoaded(secondScreenViewModel = secondScreenViewModel,  state.data)
                is WeatherCurrentUiState.Error -> ErrorMessage(message = state.msg, secondScreenViewModel = secondScreenViewModel)
            }
        }
    }
}

@Composable
fun WeatherLoaded(
    secondScreenViewModel: SecondScreenViewModel,
    data: WeatherCurrentData
) {
    var num by remember { mutableStateOf(1) }

    Row {
        Text(text = "City: ", style = MaterialTheme.typography.h6)
        Text(text = data.name, style = MaterialTheme.typography.h6)
    }

    Spacer(modifier = Modifier.size(10.dp))

    Row {
        Text(text = "Country: ", style = MaterialTheme.typography.h6)
        Text(text = data.country, style = MaterialTheme.typography.h6)
    }

    Spacer(modifier = Modifier.size(10.dp))

    Row {

        Text(text = "Temp: ", style = MaterialTheme.typography.h6)
        Text(text = data.temp_c, style = MaterialTheme.typography.h6)
    }

    Spacer(modifier = Modifier.size(30.dp))

    Button(
        onClick = {
            num++
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "$num")
    }

    Spacer(modifier = Modifier.size(10.dp))

    Button(
        onClick = {
            secondScreenViewModel.getEvent(
                intent = SecondScreenIntent.ForecastWeatherListClick(cityName = data.name)
            )
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "Get forecast")
    }
}

@Composable
fun ErrorMessage(
    message: String,
    secondScreenViewModel: SecondScreenViewModel
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 50.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(6.dp),
            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = message,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        secondScreenViewModel.getEvent(
                            intent = SecondScreenIntent.NavigateToPreviousScreen
                        )
                }) {
                    Text(text = "Refresh")
                }
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SecondScreenPreview() {
//    SecondScreen(cityName = "test")
//}