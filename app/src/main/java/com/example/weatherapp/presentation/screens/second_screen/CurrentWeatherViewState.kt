package com.example.weatherapp.presentation.screens.second_screen

import com.example.weatherapp.domain.models.WeatherCurrentData
import com.example.weatherapp.redux.State

/** State shows the configuration of the current weather screen at the given time */

data class CurrentWeatherViewState(
    val weatherCurrentData: WeatherCurrentData? = null,
    val showLoading: Boolean = true,
    val error: String? = null
): State