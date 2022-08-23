package com.example.weatherapp.presentation.screens.second_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.repository.CurrentWeatherRepositoryImpl

class SecondScreenViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        NavHostController(context)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SecondScreenViewModel(navController) as T
    }
}