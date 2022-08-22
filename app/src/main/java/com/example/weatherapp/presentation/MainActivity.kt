package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.screens.first_screen.FirstScreen
import com.example.weatherapp.presentation.screens.second_screen.SecondScreen
import com.example.weatherapp.presentation.screens.third_screen.ThirdScreen


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

        NavHost(
            navController = navController,
            startDestination = NavRoutes.FirstScreen.route,
        ) {
            composable(NavRoutes.FirstScreen.route) {
                FirstScreen(navController = navController)
            }

            composable(NavRoutes.SecondScreen.route + "/{cityName}") { backStackEntry ->
                val cityName = backStackEntry.arguments?.getString("cityName")
                SecondScreen(navController = navController, cityName = cityName)
            }

            composable(NavRoutes.ThirdScreen.route + "/{cityName}") { backStackEntry ->
                val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
                ThirdScreen(navController = navController, cityName = cityName)
            }
        }
    }
}