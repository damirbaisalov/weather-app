package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class LocationApiData(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)
