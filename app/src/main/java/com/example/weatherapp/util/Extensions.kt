package com.example.weatherapp.util

import com.example.weatherapp.data.models.current_weather.WeatherApiData
import com.example.weatherapp.data.models.forecast_weather.WeatherForecastApiData
import com.example.weatherapp.domain.models.WeatherCurrentData
import com.example.weatherapp.domain.models.WeatherForecastData

fun WeatherApiData.mapToDomain() = WeatherCurrentData(
    name = location.name,
    country = location.country,
    temp_c = current.temp_c.toString()
)

fun WeatherForecastApiData.mapToDomain() : List<WeatherForecastData> {

    val weatherForecastDataList = mutableListOf<WeatherForecastData>()

    for (weatherForecastDayApiData in forecast.forecastDay) {
        val w = WeatherForecastData(
            date = weatherForecastDayApiData.date,
            avgTempC = weatherForecastDayApiData.day.avgTempC.toString()
        )
        weatherForecastDataList.add(w)
    }

    return weatherForecastDataList
}