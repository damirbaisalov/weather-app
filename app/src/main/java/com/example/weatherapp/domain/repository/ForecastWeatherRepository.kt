package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.models.WeatherForecastData
import com.example.weatherapp.domain.models.WeatherLocation

interface ForecastWeatherRepository {

    suspend fun getForecastWeather(weatherLocation: WeatherLocation): List<WeatherForecastData>
}