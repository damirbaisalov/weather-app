package com.example.weatherapp.data.models.current_weather

import com.google.gson.annotations.SerializedName

data class CurrentApiData(
    @SerializedName("temp_c") val temp_c: Float
)
