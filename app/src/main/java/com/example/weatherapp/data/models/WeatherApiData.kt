package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WeatherApiData(
    @SerializedName("location") val location: LocationApiData,
    @SerializedName("current") val current: CurrentApiData
)