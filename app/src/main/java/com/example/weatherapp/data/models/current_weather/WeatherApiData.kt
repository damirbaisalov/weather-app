package com.example.weatherapp.data.models.current_weather

import com.google.gson.annotations.SerializedName

data class WeatherApiData(
    @SerializedName("location") val location: LocationApiData,
    @SerializedName("current") val current: CurrentApiData
)