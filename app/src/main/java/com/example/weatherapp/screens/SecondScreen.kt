package com.example.weatherapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.NavRoutes

@Composable
fun SecondScreen(navController: NavHostController, cityName: String?) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row {
               Text(text = "City: ", style = MaterialTheme.typography.h6)
               Text(text = cityName.toString(), style = MaterialTheme.typography.h6)
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row {
                val countryName = ""
                Text(text = "Country: ", style = MaterialTheme.typography.h6)
                Text(text = countryName, style = MaterialTheme.typography.h6)
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row {
                val countryName = ""
                Text(text = "Temp: ", style = MaterialTheme.typography.h6)
                Text(text = countryName, style = MaterialTheme.typography.h6)
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
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(navController = rememberNavController(), cityName = "test")
}