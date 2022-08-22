package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ApiRetrofit
import com.example.weatherapp.data.models.current_weather.WeatherApiState
import com.example.weatherapp.data.models.forecast_weather.WeatherForecastApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _weatherApiState = MutableStateFlow<WeatherApiState>(WeatherApiState.Empty)
    val weatherApiState: StateFlow<WeatherApiState> = _weatherApiState

    private val _weatherForecastApiState = MutableStateFlow<WeatherForecastApiState>(
        WeatherForecastApiState.Empty)
    val weatherForeCastApiState: StateFlow<WeatherForecastApiState> = _weatherForecastApiState

    fun getCurrentWeather(cityName: String) = viewModelScope.launch {

        _weatherApiState.value = WeatherApiState.Loading

        withContext(Dispatchers.IO) {
            try {
                val weatherApiData = ApiRetrofit.getApiClient().getCurrentWeather(
                    key = ApiRetrofit.API_KEY,
                    cityName = cityName
                )
                _weatherApiState.value = WeatherApiState.Success(weatherApiData)
            } catch (e: Exception) {
                _weatherApiState.value = WeatherApiState.Error(e.message.toString())
            }
        }
    }

    fun getForecastWeather(cityName: String, days: String) = viewModelScope.launch {

        _weatherForecastApiState.value = WeatherForecastApiState.Loading

        withContext(Dispatchers.IO) {
            try {
                val weatherForecastDayApiData = ApiRetrofit.getApiClient().getForecastWeather(
                    key = ApiRetrofit.API_KEY,
                    cityName = cityName,
                    days = days
                )
                _weatherForecastApiState.value = WeatherForecastApiState.Success(weatherForecastDayApiData.forecast.forecastDay)
            } catch (e: Exception) {
                _weatherForecastApiState.value = WeatherForecastApiState.Error(e.message.toString())
            }
        }
    }

}