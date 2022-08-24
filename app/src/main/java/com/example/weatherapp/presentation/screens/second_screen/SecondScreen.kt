package com.example.weatherapp.presentation.screens.second_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.domain.models.WeatherCurrentData

@Composable
fun SecondScreen(navController: NavHostController, cityName: String) {

    val secondScreenViewModel: SecondScreenViewModel = viewModel(
        factory = SecondScreenViewModelFactory(navController)
    )

    val viewState = secondScreenViewModel.viewState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {

        secondScreenViewModel.getCurrentWeather(cityName)

        Log.e("LAUNCHED", "${viewState.value}")
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

            if (viewState.value.showLoading) {
                ShowProgressbar()
            } else {
                if (viewState.value.weatherCurrentData != null) {
                    WeatherLoaded(
                        secondScreenViewModel = secondScreenViewModel,
                        data = viewState.value.weatherCurrentData!!
                    )
                } else {
                    ErrorMessage(
                        message = viewState.value.error!!,
                        secondScreenViewModel = secondScreenViewModel
                    )
                }

            }
        }
    }
}

@Composable
fun WeatherLoaded(
    secondScreenViewModel: SecondScreenViewModel,
    data: WeatherCurrentData
) {
    val cityNameAlmaty = "Almaty"
    val newCityName = "Pavlodar"

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

    Row {

        Button(
            onClick = {
                secondScreenViewModel.performAction(
                    action = CurrentWeatherAction.CurrentWeatherCityNameChanged(
                        cityName = cityNameAlmaty
                    )
                )
            },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = cityNameAlmaty)
        }

        Spacer(modifier = Modifier.padding(horizontal = 10.dp))

        Button(
            onClick = {
                secondScreenViewModel.performAction(
                    action = CurrentWeatherAction.CurrentWeatherCityNameChanged(
                    cityName = newCityName
                    )
                )
            },
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = newCityName)
        }
    }

    Spacer(modifier = Modifier.size(10.dp))

    Button(
        onClick = {
            secondScreenViewModel.performAction(
                action = CurrentWeatherAction.GetForecastButtonClicked(cityName = data.name)
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
                        secondScreenViewModel.performAction(
                            action = CurrentWeatherAction.RefreshButtonClicked
                        )
                }) {
                    Text(text = "Refresh")
                }
            }

        }
    }
}

@Composable
fun ShowProgressbar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(
        cityName = "test",
        navController = rememberNavController()
    )
}