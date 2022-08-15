package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.data.ApiRetrofit
import com.example.weatherapp.data.models.WeatherApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.lang.Exception

class MainFragmentViewModel : ViewModel() {

    private val progressLiveData = MutableLiveData<Boolean>()
    val getResultProgressData: LiveData<Boolean> = progressLiveData

    private val resultWeatherLiveData = MutableLiveData<WeatherApiData>()
    val getResultWeatherData: LiveData<WeatherApiData> = resultWeatherLiveData

    fun getCurrentWeatherIO() {

        progressLiveData.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val response = ApiRetrofit.getApiClient().getCurrentWeather()
            if (response.isSuccessful) {
                resultWeatherLiveData.postValue(response.body())
            }
        }

        progressLiveData.value = false
    }

    fun getCurrentWeatherMain() {

        viewModelScope.launch(Dispatchers.Main) {

            progressLiveData.value = true

            val response = withContext(Dispatchers.IO) {
                ApiRetrofit.getApiClient().getCurrentWeather()
            }

            if (response.isSuccessful) {
                resultWeatherLiveData.value = response.body()
            }

            progressLiveData.value = false
        }
    }
}