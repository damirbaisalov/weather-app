package com.example.weatherapp.presentation.screens.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

class SecondScreenViewModelFactory(private val navController: NavHostController): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SecondScreenViewModel(navController) as T
    }
}