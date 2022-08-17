package com.example.weatherapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofit {

    const val BASE_URL = "http://api.weatherapi.com/v1/"
    const val API_KEY = "d17846b1c4684119a2764436221508"

    fun getApiClient(): ApiClient {
        val apiRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return apiRetrofit.create(ApiClient::class.java)
    }
}