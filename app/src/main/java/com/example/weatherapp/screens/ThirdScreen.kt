package com.example.weatherapp.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.data.models.forecast_weather.WeatherForecastApiState
import com.example.weatherapp.data.models.forecast_weather.WeatherForecastDayDetailsApiData

@Composable
fun ThirdScreen(navController: NavHostController, cityName: String, mainViewModel: MainViewModel = viewModel()) {

    val viewState = mainViewModel.weatherForeCastApiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getForecastWeather(
            cityName = cityName,
            days = "7"
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
            is WeatherForecastApiState.Empty -> Text(
                text = "Empty state occurred"
            )
            is WeatherForecastApiState.Loading -> Loading()
            is WeatherForecastApiState.Success -> ForecastLoaded(weatherForecastDayDetailList = state.data)
            is WeatherForecastApiState.Error -> ErrorMessage(
                                                    message = state.msg,
                                                    navController = navController
                                                )
        }
    }

}

@Composable
fun ForecastLoaded(weatherForecastDayDetailList: List<WeatherForecastDayDetailsApiData>) {

    val context = LocalContext.current

    LazyColumn {
        for (weatherForecastDayDetail in weatherForecastDayDetailList) {
            item {
                    Card(
                        elevation = 8.dp,
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 50.dp, vertical = 5.dp)
                            .fillMaxWidth()
                            .clickable {
                                showToast(context, weatherForecastDayDetail.toString())
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){

                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .padding(start = 20.dp),
                                text = weatherForecastDayDetail.date,
                                textAlign = TextAlign.Start
                            )
                            
                            Spacer(modifier = Modifier.padding(10.dp))

                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .padding(end = 20.dp),
                                text = weatherForecastDayDetail.day.avgTempC.toString(),
                                textAlign = TextAlign.End
                            )
                        }
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

fun showToast(context: Context,  message: String ) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}