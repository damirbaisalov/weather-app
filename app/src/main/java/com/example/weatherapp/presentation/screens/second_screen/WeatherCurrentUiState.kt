package com.example.weatherapp.presentation.screens.second_screen

import com.example.weatherapp.domain.models.WeatherCurrentData

sealed class WeatherCurrentUiState {
    class Success(val data: WeatherCurrentData): WeatherCurrentUiState()
    class Error(val msg: String): WeatherCurrentUiState()
    object Loading: WeatherCurrentUiState()
    object Empty: WeatherCurrentUiState()
}
