package com.example.weatherapp.data

import com.example.weatherapp.data.models.WeatherApiData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {

    @FormUrlEncoded
    @POST("current.json?")
    suspend fun getCurrentWeather(
        @Field("key") key : String,
        @Field("q") cityName: String
    ): WeatherApiData

}