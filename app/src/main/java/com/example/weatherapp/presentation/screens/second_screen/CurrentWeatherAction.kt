package com.example.weatherapp.presentation.screens.second_screen

import com.example.weatherapp.domain.models.WeatherCurrentData
import com.example.weatherapp.redux.Action

sealed class CurrentWeatherAction: Action {

    data class CurrentWeatherCityNameChanged(val cityName: String): CurrentWeatherAction()
    data class CurrentWeatherLoaded(val newCurrentWeatherData: WeatherCurrentData): CurrentWeatherAction()
    object CurrentWeatherLoading: CurrentWeatherAction()
    class CurrentWeatherFailed(val error: String?): CurrentWeatherAction()
    class GetForecastButtonClicked(val cityName: String): CurrentWeatherAction()
    object RefreshButtonClicked: CurrentWeatherAction()
}