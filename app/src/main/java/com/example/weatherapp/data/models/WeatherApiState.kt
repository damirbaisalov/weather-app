package com.example.weatherapp.data.models

import com.example.weatherapp.MainFragmentViewModel

sealed class WeatherApiState {
    class Success(val data: WeatherApiData): WeatherApiState()
    class Error(val msg: String): WeatherApiState()
    object Loading: WeatherApiState()
    object Empty: WeatherApiState()
}
