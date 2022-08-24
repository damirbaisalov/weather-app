package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.ApiRetrofit
import com.example.weatherapp.domain.models.WeatherCurrentData
import com.example.weatherapp.domain.models.WeatherLocation
import com.example.weatherapp.domain.repository.CurrentWeatherRepository
import com.example.weatherapp.util.mapToDomain

class CurrentWeatherRepositoryImpl: CurrentWeatherRepository {

    override suspend fun getCurrentWeather(weatherLocation: WeatherLocation): WeatherCurrentData {

        val weatherCurrentApiData = ApiRetrofit.getApiClient().getCurrentWeather(
            key = ApiRetrofit.API_KEY,
            cityName = weatherLocation.cityName
        )

        return weatherCurrentApiData.mapToDomain()
    }
}