package com.example.weatherapp.presentation.screens.third_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

class ThirdScreenViewModelFactory(private val navController: NavHostController): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ThirdScreenViewModel(navController) as T
    }
}