package com.example.weatherapp.screens

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
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.NavRoutes
import com.example.weatherapp.data.models.current_weather.WeatherApiData
import com.example.weatherapp.data.models.current_weather.WeatherApiState

@Composable
fun SecondScreen(navController: NavHostController, cityName: String?, mainViewModel: MainViewModel = viewModel()) {

    val viewState = mainViewModel.weatherApiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getCurrentWeather(cityName = cityName.toString())
        Log.e("get", "API CALLED")
    } )

    SideEffect {
        Log.e("side", "$viewState")
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(val state = viewState.value) {
                is WeatherApiState.Empty -> Text(
                    text = "Empty state occurred"
                )
                is WeatherApiState.Loading ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                is WeatherApiState.Success -> WeatherLoaded(navController = navController, state.data)
                is WeatherApiState.Error -> ErrorMessage(message = state.msg, navController)
            }
        }
    }
}

@Composable
fun WeatherLoaded(navController: NavHostController, data: WeatherApiData) {
    var num by remember { mutableStateOf(1) }

    Row {
        Text(text = "City: ", style = MaterialTheme.typography.h6)
        Text(text = data.location.name, style = MaterialTheme.typography.h6)
    }

    Spacer(modifier = Modifier.size(10.dp))

    Row {
        Text(text = "Country: ", style = MaterialTheme.typography.h6)
        Text(text = data.location.country, style = MaterialTheme.typography.h6)
    }

    Spacer(modifier = Modifier.size(10.dp))

    Row {

        Text(text = "Temp: ", style = MaterialTheme.typography.h6)
        Text(text = data.current.temp_c.toString(), style = MaterialTheme.typography.h6)
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
            navController.navigate(NavRoutes.ThirdScreen.route+ "/${data.location.name}")
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "Get forecast")
    }
}

@Composable
fun ErrorMessage(message: String, navController: NavHostController) {
    Log.e("REQ_ERROR", message)
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
                        navController.navigate(NavRoutes.FirstScreen.route)
                }) {
                    Text(text = "Refresh")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(navController = rememberNavController(), cityName = "test")
}