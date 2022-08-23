package com.example.weatherapp.presentation.screens.third_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.weatherapp.data.repository.ForecastWeatherRepositoryImpl
import com.example.weatherapp.domain.models.WeatherLocation
import com.example.weatherapp.presentation.NavRoutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThirdScreenViewModel(
    private val navHostController: NavHostController
): ViewModel() {

    private val forecastWeatherRepository = ForecastWeatherRepositoryImpl()

    private val _weatherForecastUiState = MutableStateFlow<WeatherForecastUiState>(
        WeatherForecastUiState.Empty
    )
    val weatherForeCastUiState: StateFlow<WeatherForecastUiState> = _weatherForecastUiState

    fun getEvent(intent: ThirdScreenIntent) {
        when(intent) {
            is ThirdScreenIntent.ForecastWeatherFetch -> {
                getForecastWeather(
                    cityName = intent.cityName,
                    days = intent.days
                )
            }
            is ThirdScreenIntent.NavigateToFirstScreen -> {
                navHostController.navigate(NavRoutes.FirstScreen.route)
            }
        }
    }

    private fun getForecastWeather(cityName: String, days: String) = viewModelScope.launch {

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