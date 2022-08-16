package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.Home
import com.example.weatherapp.screens.Profile
import com.example.weatherapp.screens.Welcome


class MainActivity : ComponentActivity()
{
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
            startDestination = NavRoutes.Home.route,
        ) {
            composable(NavRoutes.Home.route) {
                Home(navController = navController)
            }

            composable(NavRoutes.Welcome.route + "/{userName}") { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName")
                Welcome(navController = navController, userName = userName)
            }

            composable(NavRoutes.Profile.route) {
                Profile()
            }
        }
    }
}