package com.example.weatherapp


sealed class NavRoutes(val route: String) {
    object FirstScreen : NavRoutes("FirstScreen")
    object SecondScreen : NavRoutes("SecondScreen")
    object ThirdScreen : NavRoutes("ThirdScreen")
}