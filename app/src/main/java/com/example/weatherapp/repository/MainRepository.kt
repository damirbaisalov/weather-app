package com.example.weatherapp.repository

import com.example.weatherapp.data.ApiRetrofit
import com.example.weatherapp.data.models.WeatherApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val apiRetrofit: ApiRetrofit) {

    fun getWeatherData(): Flow<WeatherApiData> = flow {
        emit(apiRetrofit.getApiClient().getCurrentWeather())
    }.flowOn(Dispatchers.IO)

}