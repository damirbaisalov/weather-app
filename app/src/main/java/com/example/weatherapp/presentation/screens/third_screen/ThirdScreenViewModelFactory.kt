package com.example.weatherapp.presentation.screens.third_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repository.ForecastWeatherRepositoryImpl

class ThirdScreenViewModelFactory: ViewModelProvider.Factory {

    private val forecastWeatherRepository by lazy(LazyThreadSafetyMode.NONE) {
        ForecastWeatherRepositoryImpl()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ThirdScreenViewModel(forecastWeatherRepository) as T
    }

}