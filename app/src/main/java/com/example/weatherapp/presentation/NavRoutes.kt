package com.example.weatherapp.presentation

sealed class NavRoutes(val route: String) {
    object FirstScreen : NavRoutes("FirstScreen")
    object SecondScreen : NavRoutes("SecondScreen")
    object ThirdScreen : NavRoutes("ThirdScreen")
}