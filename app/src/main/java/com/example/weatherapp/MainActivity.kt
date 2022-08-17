package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.*


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

            composable(NavRoutes.ThirdScreen.route) {
                ThirdScreen()
            }
        }
    }
}