package com.example.weatherapp.presentation.screens.third_screen

import com.example.weatherapp.domain.models.WeatherForecastData

sealed class ThirdScreenIntent {
    class ForecastWeatherFetch(val cityName: String, val days: String): ThirdScreenIntent()
    class ForecastWeatherClick(val weatherForecastData: WeatherForecastData): ThirdScreenIntent()
    object NavigateToFirstScreen: ThirdScreenIntent()
}
