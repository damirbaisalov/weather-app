package com.example.weatherapp.presentation.screens.third_screen

sealed class ThirdScreenIntent {
    class ForecastWeatherFetch(val cityName: String, val days: String) : ThirdScreenIntent()
    object NavigateToFirstScreen: ThirdScreenIntent()
}
