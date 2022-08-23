package com.example.weatherapp.presentation.screens.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.weatherapp.data.repository.CurrentWeatherRepositoryImpl
import com.example.weatherapp.domain.models.WeatherLocation
import com.example.weatherapp.presentation.NavRoutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondScreenViewModel(
    private val navHostController: NavHostController
): ViewModel() {

    private val currentWeatherRepository = CurrentWeatherRepositoryImpl()

    private val _weatherCurrentUiState = MutableStateFlow<WeatherCurrentUiState>(WeatherCurrentUiState.Empty)
    val weatherCurrentUiState: StateFlow<WeatherCurrentUiState> = _weatherCurrentUiState


    fun getIntent(intent: SecondScreenIntent) {
        when(intent) {
            is SecondScreenIntent.CurrentWeatherFetch -> {
                getCurrentWeather(intent.cityName.toString())
            }
            is SecondScreenIntent.ForecastWeatherListClick -> {
                navHostController.navigate(NavRoutes.ThirdScreen.route + "/${intent.cityName}")
            }
            is SecondScreenIntent.NavigateToPreviousScreen -> {
                navHostController.popBackStack()
            }
        }
    }

    private fun getCurrentWeather(cityName: String) = viewModelScope.launch {

        _weatherCurrentUiState.value = WeatherCurrentUiState.Loading

        withContext(Dispatchers.IO) {
            try {
                val weatherData = currentWeatherRepository.getCurrentWeather(
                    WeatherLocation(
                        cityName = cityName,
                        days = ""
                    )
                )
                _weatherCurrentUiState.value = WeatherCurrentUiState.Success(weatherData)
            } catch (e: Exception) {
                _weatherCurrentUiState.value = WeatherCurrentUiState.Error(e.message.toString())
            }
        }
    }

}