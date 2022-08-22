package com.example.weatherapp.presentation.screens.third_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.ForecastWeatherRepositoryImpl
import com.example.weatherapp.domain.models.WeatherLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThirdScreenViewModel(
    private val forecastWeatherRepository: ForecastWeatherRepositoryImpl
): ViewModel() {

    private val _weatherForecastUiState = MutableStateFlow<WeatherForecastUiState>(
        WeatherForecastUiState.Empty
    )
    val weatherForeCastUiState: StateFlow<WeatherForecastUiState> = _weatherForecastUiState

    fun getForecastWeather(cityName: String, days: String) = viewModelScope.launch {

        _weatherForecastUiState.value = WeatherForecastUiState.Loading

        withContext(Dispatchers.IO) {
            try {
                val weatherForecastData = forecastWeatherRepository.getForecastWeather(
                    WeatherLocation(
                        cityName = cityName,
                        days = days
                    )
                )
                _weatherForecastUiState.value = WeatherForecastUiState.Success(weatherForecastData)
            } catch (e: Exception) {
                _weatherForecastUiState.value = WeatherForecastUiState.Error(e.message.toString())
            }
        }
    }
}