package com.example.weatherapp.presentation.screens.first_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController

class FirstScreenViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        NavHostController(context)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FirstScreenViewModel(navController) as T
    }
}