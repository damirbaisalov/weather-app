package com.example.weatherapp.presentation.screens.second_screen

sealed class SecondScreenIntent {
    class CurrentWeatherFetch(val cityName: String?) : SecondScreenIntent()
    class ForecastWeatherListClick(val cityName: String): SecondScreenIntent()
    object NavigateToPreviousScreen: SecondScreenIntent()
}
