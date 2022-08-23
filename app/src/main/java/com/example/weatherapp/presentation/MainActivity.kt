package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.screens.first_screen.FirstScreen
import com.example.weatherapp.presentation.screens.first_screen.FirstScreenViewModel
import com.example.weatherapp.presentation.screens.first_screen.FirstScreenViewModelFactory
import com.example.weatherapp.presentation.screens.second_screen.SecondScreen
import com.example.weatherapp.presentation.screens.second_screen.SecondScreenViewModel
import com.example.weatherapp.presentation.screens.third_screen.ThirdScreen
import com.example.weatherapp.presentation.screens.third_screen.ThirdScreenViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {

        val navController = rememberNavController()

        val firstScreenViewModel =  FirstScreenViewModel(navController)
        val secondScreenViewModel = SecondScreenViewModel(navController)
        val thirdScreenViewModel = ThirdScreenViewModel(navController)

        NavHost(
            navController = navController,
            startDestination = NavRoutes.FirstScreen.route,
        ) {
            composable(NavRoutes.FirstScreen.route) {
                FirstScreen(firstScreenViewModel)
            }

            composable(NavRoutes.SecondScreen.route + "/{cityName}") { backStackEntry ->
                val cityName = backStackEntry.arguments?.getString("cityName", "") ?: ""
                SecondScreen(secondScreenViewModel ,cityName = cityName)
            }

            composable(NavRoutes.ThirdScreen.route + "/{cityName}") { backStackEntry ->
                val cityName = backStackEntry.arguments?.getString("cityName", "") ?: ""
                ThirdScreen(thirdScreenViewModel, cityName = cityName)
            }
        }
    }
}