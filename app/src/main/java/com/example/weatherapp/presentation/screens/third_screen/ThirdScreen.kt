package com.example.weatherapp.presentation.screens.third_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.models.WeatherForecastData

@Composable
fun ThirdScreen(thirdScreenViewModel: ThirdScreenViewModel,cityName: String) {

//    val thirdScreenViewModel: ThirdScreenViewModel = viewModel(
//        factory = ThirdScreenViewModelFactory(LocalContext.current)
//    )
    val viewState = thirdScreenViewModel.weatherForeCastUiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        thirdScreenViewModel.getIntent(
            ThirdScreenIntent.ForecastWeatherFetch(
                cityName = cityName,
                days = "7"
            )
        )

        Log.e("launched_forecast", viewState.value.toString())
    })

    SideEffect {
        Log.e("side_forecast", viewState.value.toString())
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when(val state = viewState.value) {
            is WeatherForecastUiState.Empty -> Text(
                text = "Empty state occurred"
            )
            is WeatherForecastUiState.Loading -> Loading()
            is WeatherForecastUiState.Success -> ForecastLoaded(
                                                    weatherForecastDataList = state.data,
                                                    thirdScreenViewModel = thirdScreenViewModel
                                                )
            is WeatherForecastUiState.Error -> ErrorMessage(
                                                    message = state.msg,
                                                    thirdScreenViewModel = thirdScreenViewModel
                                                )
        }
    }

}

@Composable
fun ForecastLoaded(
    weatherForecastDataList: List<WeatherForecastData>,
    thirdScreenViewModel: ThirdScreenViewModel
) {

    LazyColumn {
        for (weatherForecastData in weatherForecastDataList) {
            item {
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 50.dp, vertical = 5.dp)
                            .fillMaxWidth()
                            .clickable {
                                thirdScreenViewModel.getIntent(
                                    ThirdScreenIntent.ForecastWeatherClick(
                                        weatherForecastData = weatherForecastData
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){

                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .padding(start = 20.dp),
                                text = weatherForecastData.date,
                                textAlign = TextAlign.Start
                            )
                            
                            Spacer(modifier = Modifier.padding(10.dp))

                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .padding(end = 20.dp),
                                text = weatherForecastData.avgTempC,
                                textAlign = TextAlign.End
                            )
                        }
                    }
            }
        }
    }
}

@Composable
fun ErrorMessage(
    message: String,
    thirdScreenViewModel: ThirdScreenViewModel
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
                        thirdScreenViewModel.getIntent(
                            intent = ThirdScreenIntent.NavigateToFirstScreen
                        )
                    }) {
                    Text(text = "Refresh")
                }
            }

        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}