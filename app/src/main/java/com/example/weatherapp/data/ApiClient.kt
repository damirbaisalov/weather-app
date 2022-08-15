package com.example.weatherapp.data

import com.example.weatherapp.data.models.WeatherApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("/current.json")
    fun getCurrentWeather(): Response<WeatherApiData>

}