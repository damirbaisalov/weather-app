package com.example.weatherapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.models.WeatherApiData

class MainViewModel: ViewModel() {

//    private val _weatherApiState = MutableStateFlow<WeatherApiState>(WeatherApiState.Empty)
//    val weatherApiState: StateFlow<WeatherApiState> = _weatherApiState

    var weatherApiData: WeatherApiData by mutableStateOf()

}