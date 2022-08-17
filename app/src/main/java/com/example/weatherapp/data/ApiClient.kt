package com.example.weatherapp.data

import com.example.weatherapp.data.ApiRetrofit.API_KEY
import com.example.weatherapp.data.models.WeatherApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("current.json?key=d17846b1c4684119a2764436221508&q=Pavlodar")
    suspend fun getCurrentWeather(): WeatherApiData

}