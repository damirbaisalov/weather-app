package com.example.weatherapp.presentation.screens.first_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

class FirstScreenViewModelFactory(private val navController: NavHostController): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FirstScreenViewModel(navController) as T
    }
}