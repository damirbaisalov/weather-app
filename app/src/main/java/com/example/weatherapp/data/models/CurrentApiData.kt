package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class CurrentApiData(
    @SerializedName("temp_c") val temp_c: Float
)
