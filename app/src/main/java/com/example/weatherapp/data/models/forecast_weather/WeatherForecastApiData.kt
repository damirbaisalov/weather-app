package com.example.weatherapp.data.models.forecast_weather

import com.google.gson.annotations.SerializedName

data class WeatherForecastApiData(
    @SerializedName("forecast") val forecast: WeatherForecastDayApiData
)

data class WeatherForecastDayApiData(
    @SerializedName("forecastday") val forecastDay: List<WeatherForecastDayDetailsApiData>
)

data class WeatherForecastDayDetailsApiData(
    @SerializedName("date") val date : String,
    @SerializedName("day") val day: WeatherForecastDayTempApiData
)

data class WeatherForecastDayTempApiData(
    @SerializedName("avgtemp_c") val avgTempC: Float
)