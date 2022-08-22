package com.example.weatherapp.data.models.current_weather

sealed class WeatherApiState {
    class Success(val data: WeatherApiData): WeatherApiState()
    class Error(val msg: String): WeatherApiState()
    object Loading: WeatherApiState()
    object Empty: WeatherApiState()
}
