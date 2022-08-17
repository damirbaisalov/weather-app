package com.example.weatherapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.NavRoutes
import com.example.weatherapp.data.models.WeatherApiData
import com.example.weatherapp.data.models.WeatherApiState

@Composable
fun SecondScreen(navController: NavHostController, cityName: String?, mainViewModel: MainViewModel = viewModel()) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(val state = mainViewModel.weatherApiState.collectAsState().value) {
                is WeatherApiState.Empty -> Text(
                    text = "Empty"
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
                is WeatherApiState.Error -> ErrorDialog(message = state.msg)
            }
        }
    }
}

@Composable
fun WeatherLoaded(navController: NavHostController, data: WeatherApiData) {
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
            navController.navigate(NavRoutes.FirstScreen.route)
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "Find weather")
    }

    Spacer(modifier = Modifier.size(10.dp))

    Button(
        onClick = {
            navController.navigate(NavRoutes.ThirdScreen.route)
        },
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = "City list")
    }
}

@Composable
fun ErrorDialog(message: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Error occurred")
            },
            text = {
                Text(message)
            },
            confirmButton = {
                openDialog.value = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(navController = rememberNavController(), cityName = "test")
}