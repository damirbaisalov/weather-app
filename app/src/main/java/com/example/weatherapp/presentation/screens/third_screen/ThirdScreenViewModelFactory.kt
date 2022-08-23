package com.example.weatherapp.presentation.screens.third_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.weatherapp.data.repository.ForecastWeatherRepositoryImpl
import com.example.weatherapp.presentation.screens.second_screen.SecondScreenViewModel

class ThirdScreenViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        NavHostController(context)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ThirdScreenViewModel(navController) as T
    }

}