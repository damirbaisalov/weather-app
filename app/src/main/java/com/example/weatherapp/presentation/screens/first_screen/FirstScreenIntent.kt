package com.example.weatherapp.presentation.screens.first_screen

sealed class FirstScreenIntent {
    class NavigateToSecondScreen(val cityName: String): FirstScreenIntent()
}
