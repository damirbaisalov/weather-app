package com.example.weatherapp.data.models.forecast_weather

sealed class WeatherForecastApiState {
    class Success(val data: List<WeatherForecastDayDetailsApiData>): WeatherForecastApiState()
    class Error(val msg: String): WeatherForecastApiState()
    object Loading: WeatherForecastApiState()
    object Empty: WeatherForecastApiState()
}