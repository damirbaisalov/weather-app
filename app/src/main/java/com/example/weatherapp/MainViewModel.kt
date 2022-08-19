package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.ApiRetrofit
import com.example.weatherapp.data.models.WeatherApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _weatherApiState = MutableStateFlow<WeatherApiState>(WeatherApiState.Empty)
    val weatherApiState: StateFlow<WeatherApiState> = _weatherApiState

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

}