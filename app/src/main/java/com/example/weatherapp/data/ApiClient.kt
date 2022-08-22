package com.example.weatherapp.data

import com.example.weatherapp.data.models.current_weather.WeatherApiData
import com.example.weatherapp.data.models.forecast_weather.WeatherForecastApiData
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {

    @POST("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") cityName: String
    ): WeatherApiData

    @POST("forecast.json")
    suspend fun getForecastWeather(
        @Query("key") key: String,
        @Query("q") cityName: String,
        @Query("days") days: String
    ): WeatherForecastApiData

}