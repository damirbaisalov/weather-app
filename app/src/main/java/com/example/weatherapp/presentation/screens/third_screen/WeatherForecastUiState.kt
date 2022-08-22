package com.example.weatherapp.presentation.screens.third_screen

import com.example.weatherapp.domain.models.WeatherForecastData

sealed class WeatherForecastUiState {
    class Success(val data: List<WeatherForecastData>): WeatherForecastUiState()
    class Error(val msg: String): WeatherForecastUiState()
    object Loading: WeatherForecastUiState()
    object Empty: WeatherForecastUiState()
}