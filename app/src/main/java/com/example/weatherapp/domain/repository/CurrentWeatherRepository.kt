package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.models.WeatherCurrentData
import com.example.weatherapp.domain.models.WeatherLocation

interface CurrentWeatherRepository {

    suspend fun getCurrentWeather(weatherLocation: WeatherLocation): WeatherCurrentData?
}