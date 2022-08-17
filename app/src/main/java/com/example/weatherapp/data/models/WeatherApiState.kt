package com.example.weatherapp.data.models

sealed class WeatherApiState {
    class Success(val data: WeatherApiData): WeatherApiState()
    class Error(val msg: String): WeatherApiState()
    object Loading: WeatherApiState()
    object Empty: WeatherApiState()
}
