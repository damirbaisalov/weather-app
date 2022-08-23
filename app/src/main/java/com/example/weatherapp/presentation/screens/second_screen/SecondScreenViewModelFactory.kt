package com.example.weatherapp.presentation.screens.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repository.CurrentWeatherRepositoryImpl

class SecondScreenViewModelFactory: ViewModelProvider.Factory {

    private val currentWeatherRepository by lazy(LazyThreadSafetyMode.NONE) {
        CurrentWeatherRepositoryImpl()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SecondScreenViewModel(currentWeatherRepository) as T
    }
}