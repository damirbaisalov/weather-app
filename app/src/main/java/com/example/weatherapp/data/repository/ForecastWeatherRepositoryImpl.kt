package com.example.weatherapp.data.repository

import com.example.weatherapp.data.network.ApiRetrofit
import com.example.weatherapp.domain.models.WeatherForecastData
import com.example.weatherapp.domain.models.WeatherLocation
import com.example.weatherapp.domain.repository.ForecastWeatherRepository
import com.example.weatherapp.util.mapToDomain

class ForecastWeatherRepositoryImpl: ForecastWeatherRepository {

    override suspend fun getForecastWeather(weatherLocation: WeatherLocation): List<WeatherForecastData> {

        val weatherForecastApiData = ApiRetrofit.getApiClient().getForecastWeather(
            key = ApiRetrofit.API_KEY,
            cityName = weatherLocation.cityName,
            days = weatherLocation.days
        )

        return weatherForecastApiData.mapToDomain()
    }
}