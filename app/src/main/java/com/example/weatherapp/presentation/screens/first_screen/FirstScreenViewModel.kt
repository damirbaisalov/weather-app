package com.example.weatherapp.presentation.screens.first_screen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.weatherapp.presentation.NavRoutes

class FirstScreenViewModel(
    private val navHostController: NavHostController
): ViewModel() {

    fun getIntent(intent: FirstScreenIntent) {
        when(intent) {
            is FirstScreenIntent.NavigateToSecondScreen -> {
                navHostController.navigate(NavRoutes.SecondScreen.route + "/${intent.cityName}")
            }
        }
    }

}